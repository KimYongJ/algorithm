// 13553 : |A[i] – A[j]| ≤ K 쌍의 개수 (모스 + BIT 버전, Java 11 ≈ 200 ms)
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    /* ---------- 상수 & 전역 ---------- */
    static final int MAXV = 100_000;   // 값의 최대치
    static int[] bit = new int[MAXV + 1];
    static int K, BLOCK;               // BLOCK = ⌊√N⌋

    /* ---------- BIT ---------- */
    static void bitAdd(int idx, int delta) {
        for (int i = idx; i <= MAXV; i += i & -i) bit[i] += delta;
    }
    static int bitSum(int idx) {
        int s = 0;
        for (int i = idx; i > 0; i -= i & -i) s += bit[i];
        return s;
    }
    static int rangeSum(int l, int r) {               // [l, r] 빈도 합
        if (l > r) return 0;
        if (l < 1) l = 1;
        if (r > MAXV) r = MAXV;
        return bitSum(r) - bitSum(l - 1);
    }

    /* ---------- 쿼리 ---------- */
    static class Query implements Comparable<Query> {
        int l, r, idx;
        Query(int l, int r, int idx) { this.l = l; this.r = r; this.idx = idx; }
        @Override public int compareTo(Query o) {
            int b1 = l / BLOCK, b2 = o.l / BLOCK;
            if (b1 != b2) return b1 - b2;
            // 같은 블록이면 짝수‑블록은 r↑, 홀수‑블록은 r↓  (왕복 최소화)
            return (b1 & 1) == 0 ? r - o.r : o.r - r;
        }
    }

    /* ---------- 메인 ---------- */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /* 입력 */
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        K   = Integer.parseInt(st.nextToken());
        BLOCK = (int) Math.sqrt(N);

        int[] A = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) A[i] = Integer.parseInt(st.nextToken());

        int Q = Integer.parseInt(br.readLine());
        Query[] qs = new Query[Q];
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            qs[i] = new Query(l, r, i);
        }
        Arrays.sort(qs);

        /* 모스 알고리즘 */
        long[] ans = new long[Q];
        int L = 1, R = 0;
        long pairCnt = 0;

        for (Query q : qs) {
            while (R < q.r) {                       // 오른쪽 확장
                ++R;
                pairCnt += rangeSum(A[R] - K, A[R] + K);
                bitAdd(A[R], 1);
            }
            while (R > q.r) {                       // 오른쪽 축소
                bitAdd(A[R], -1);
                pairCnt -= rangeSum(A[R] - K, A[R] + K);
                --R;
            }
            while (L < q.l) {                       // 왼쪽 축소
                bitAdd(A[L], -1);
                pairCnt -= rangeSum(A[L] - K, A[L] + K);
                ++L;
            }
            while (L > q.l) {                       // 왼쪽 확장
                --L;
                pairCnt += rangeSum(A[L] - K, A[L] + K);
                bitAdd(A[L], 1);
            }
            ans[q.idx] = pairCnt;
        }

        /* 출력 */
        StringBuilder sb = new StringBuilder();
        for (long v : ans) sb.append(v).append('\n');
        System.out.print(sb);
    }
}
