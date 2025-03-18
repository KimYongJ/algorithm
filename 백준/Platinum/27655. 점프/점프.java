import java.io.*;
import java.util.*;

public class Main {

    static final int SZ = 1 << 20;  // 세그먼트 트리 크기 (2^20 = 1,048,576)

    // Lazy Segment Tree (덮어쓰기 방식)
    static class LazySeg {
        int[] T = new int[SZ << 1];  // 실제 세그먼트 트리 배열
        int[] L = new int[SZ << 1];  // lazy 배열 (구간에 "v"로 덮어쓰는 값)

        LazySeg() {
            Arrays.fill(T, 1000000000);  // 초기값을 충분히 큰 값(1e9)으로 설정
        }

        // lazy 전파
        void prop(int i, int s, int e) {
            if (L[i] == 0) return;  // lazy가 0이면(덮어쓸 값이 없으면) 그냥 반환
            T[i] = L[i];           // 이 노드 구간 전체를 L[i] 값으로 덮어씀
            if (s != e) {          // 리프가 아니면 자식들에게도 lazy 할당
                L[i << 1]     = L[i];
                L[i << 1 | 1] = L[i];
            }
            L[i] = 0;              // 현재 노드의 lazy는 처리 후 0으로 리셋
        }

        // 구간 [l, r]에 v로 덮어쓰기(Assignment)
        void upd(int l, int r, int v) {
            upd(l, r, v, 1, 0, SZ - 1);
        }
        void upd(int l, int r, int v, int i, int s, int e) {
            prop(i, s, e);
            if (r < s || e < l) return;   // 범위 밖
            if (l <= s && e <= r) {
                L[i] = v;    // 이 구간을 전부 v로 덮어쓰겠다는 lazy 설정
                prop(i, s, e);
                return;
            }
            int m = (s + e) >> 1;
            upd(l, r, v, i << 1,     s,     m);
            upd(l, r, v, i << 1 | 1, m + 1, e);
            T[i] = Math.min(T[i << 1], T[i << 1 | 1]);
        }

        // 구간 [l, r]의 최솟값
        int qry(int l, int r) {
            return qry(l, r, 1, 0, SZ - 1);
        }
        int qry(int l, int r, int i, int s, int e) {
            prop(i, s, e);
            if (r < s || e < l) return 1000000000;  // 범위 밖 => 큰 값 반환
            if (l <= s && e <= r) return T[i];
            int m = (s + e) >> 1;
            return Math.min(qry(l, r, i << 1,     s,     m),
                             qry(l, r, i << 1 | 1, m + 1, e));
        }
    }

    public static void main(String[] args) throws IOException {
        // 입력 세팅
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // n=1일 때 예외 처리 (원본 코드 흐름 유지)
        if (n == 1) {
            System.out.println(0);
            return;
        }

        // v[i] = { h, l, r } 형태로 저장
        int[][] v = new int[n][3]; 
        // 좌표압축을 위한 후보들
        ArrayList<Integer> x = new ArrayList<>();

        // 입력 (l, r, h)
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            // 주의: 원본 C++ 코드는 tuple<int,int,int>에 (h,l,r)로 담지만,
            //       여기서는 {h, l, r} 순서대로 v[i][0], v[i][1], v[i][2]에 저장
            v[i][0] = h;
            v[i][1] = l;
            v[i][2] = r;

            // 좌표압축용
            x.add(l);
            x.add(r);
        }

        // x 정렬 후 중복 제거
        Collections.sort(x);
        ArrayList<Integer> uniq = new ArrayList<>();
        uniq.add(x.get(0));
        for (int i = 1; i < x.size(); i++) {
            if (!x.get(i).equals(x.get(i - 1))) {
                uniq.add(x.get(i));
            }
        }

        // l, r 좌표압축
        for (int i = 0; i < n; i++) {
            int h = v[i][0], l = v[i][1], r = v[i][2];
            // Java의 "lower_bound" 역할: Collections.binarySearch
            int cl = Collections.binarySearch(uniq, l);
            int cr = Collections.binarySearch(uniq, r);
            v[i][0] = h;
            v[i][1] = cl;  // 압축된 l
            v[i][2] = cr;  // 압축된 r
        }

        // h(층) 기준 오름차순 정렬 (h가 같으면 그다음 인자 순으로 정렬)
        Arrays.sort(v, (a, b) -> {
            // a = {hA, lA, rA}, b = {hB, lB, rB}
            if (a[0] != b[0]) return a[0] - b[0];  // h 기준
            if (a[1] != b[1]) return a[1] - b[1];  // l 기준
            return a[2] - b[2];                   // r 기준
        });

        // Lazy 세그먼트 트리 초기화
        LazySeg seg = new LazySeg();

        int ans = 1000000000; // 1e9

        // 층(h) 오름차순으로 진행
        for (int i = 0; i < n; i++) {
            int h = v[i][0], l = v[i][1], r = v[i][2];
            if (h == 1) {
                // h=1 => 해당 구간을 1로 덮어쓰기
                seg.upd(l, r, 1);
            } else {
                // 현재 구간 최소값 mn
                int mn = seg.qry(l, r);
                // 구간을 mn+1로 덮어쓰기
                seg.upd(l, r, mn + 1);
                // 만약 k==h이면 => ans 갱신
                if (k == h) {
                    ans = Math.min(ans, mn);
                }
            }
        }

        // 결과 출력
        if (ans == 1000000000) System.out.println(-1);
        else System.out.println(ans);
    }
}
