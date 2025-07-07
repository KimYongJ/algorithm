import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] parent;   // 유니온-파인드용
    static int[] money;    // 그룹별 빚/받을 돈 합계 (루트 노드에만 의미 있음)

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 친구 수
        M = Integer.parseInt(st.nextToken());   // 남은 친구 관계 수

        parent = new int[N];
        money  = new int[N];

        // 초기화
        for (int i = 0; i < N; i++) {
            parent[i] = i;                      // 자기 자신이 루트
            money[i]  = Integer.parseInt(br.readLine());  // 개별 빚(+) / 받을 돈(-)
        }

        // 친구 관계(간선) 입력 및 합치기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        /* ------------- 판정 ------------- */
        // 혹시 모를 경로 압축 누락 대비하여 한 번 더 find 수행
        for (int i = 0; i < N; i++) find(i);

        // 각 그룹(=대표 노드)의 돈 합계가 0이어야 가능
        for (int i = 0; i < N; i++) {
            if (parent[i] == i && money[i] != 0) {
                System.out.println("IMPOSSIBLE");
                return;
            }
        }
        System.out.println("POSSIBLE");
    }

    /* --------- 유니온-파인드 ---------- */

    // 경로 압축 find
    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    // union: 두 집합을 합치면서 money 합산
    static void union(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        if (ra == rb) return;                   // 이미 같은 그룹

        // 인덱스가 작은 쪽을 새 루트로 삼음(임의 선택)
        if (ra < rb) {
            parent[rb] = ra;
            money[ra] += money[rb];
        } else {
            parent[ra] = rb;
            money[rb] += money[ra];
        }
    }
}
