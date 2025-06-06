
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int T, N, K, Q;
    static int[] parent;
    static int[] rank;          // 높이(또는 사이즈) 저장

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        /* 최대 노드 수(0~1,000,000) + 1 : 한 번만 할당 */
        parent = new int[1_000_000 + 1];
        rank   = new int[1_000_000 + 1];

        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine()); // 유저 수
            K = Integer.parseInt(br.readLine()); // 친구 관계 수

            /* 1~N 초기화 */
            for (int i = 1; i <= N; i++) {
                parent[i] = i;
                rank[i]   = 0;
            }

            /* 친구 관계 입력 → union */
            while (K-- > 0) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) + 1; // 0-based → 1-based
                int b = Integer.parseInt(st.nextToken()) + 1;
                union(a, b);
            }

            Q = Integer.parseInt(br.readLine()); // 질의 수
            sb.append("Scenario ").append(tc).append(":\n");

            while (Q-- > 0) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) + 1;
                int b = Integer.parseInt(st.nextToken()) + 1;
                sb.append(find(a) == find(b) ? 1 : 0).append('\n');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    /* 부모 찾기(경로 압축) */
    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    /* 랭크 기반 union */
    static void union(int x, int y) {
        int rx = find(x);
        int ry = find(y);
        if (rx == ry) return;

        if (rank[rx] < rank[ry]) {
            parent[rx] = ry;
        } else if (rank[rx] > rank[ry]) {
            parent[ry] = rx;
        } else {
            parent[ry] = rx;
            rank[rx]++;
        }
    }
}
