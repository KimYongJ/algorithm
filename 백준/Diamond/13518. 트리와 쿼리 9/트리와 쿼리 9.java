import java.util.*;

public class Main {
    static class Query {
        int s, e, i, k; // 쿼리 정보: 시작노드 s, 끝노드 e, 인덱스 i, 공통조상 k
    }
    static Scanner sc = new Scanner(System.in);
    static final int N = 100001;
    static List<Integer>[] graph = new ArrayList[N];
    static int[] in = new int[N], out = new int[N], rec = new int[N * 2];
    static int[][] dp = new int[N][18];
    static int[] depth = new int[N], weight = new int[N];
    static int[] nodeCount = new int[N];
    static int[] weightCount = new int[N * 10];
    static int[] answer = new int[N];
    static Query[] queries = new Query[N];

    static int n, q;
    static int o = 0, ro = 0, t = 0; // 방문 순서, Euler 순서 index, 결과 값

    public static void main(String[] args) {
        initGraph();
        dfsLCA(1, 1); // 루트부터 LCA용 깊이, dfs 방문 처리
        buildLcaTable(); // dp배열로 2^j 번째 부모 저장
        processQueries(); // 쿼리 정리 및 정렬
        solve(0, 0); // Mo's 알고리즘으로 결과 처리
    }

    // 입력 및 그래프 초기화
    static void initGraph() {

        n = sc.nextInt();
        for (int i = 0; i <= n; i++) graph[i] = new ArrayList<>();
        for (int i = 1; i <= n; i++) weight[i] = sc.nextInt(); // 노드별 값
        for (int i = 1; i < n; i++) {
            int u = sc.nextInt(), v = sc.nextInt();
            graph[u].add(v);
            graph[v].add(u);
        }
    }

    // DFS로 LCA를 위한 Euler Tour 진행
    static void dfsLCA(int node, int d) {
        depth[node] = d;
        in[node] = ++o;
        rec[++ro] = node;
        for (int next : graph[node]) {
            if (depth[next] == 0) {
                dp[next][0] = node; // 바로 위 부모 저장
                dfsLCA(next, d + 1);
            }
        }
        out[node] = ++o;
        rec[++ro] = node;
    }

    // LCA용 Sparse Table 구축 (dp[i][j] = i번 노드의 2^j번째 조상)
    static void buildLcaTable() {
        for (int j = 1; j < 18; j++) {
            for (int i = 1; i <= n; i++) {
                dp[i][j] = dp[dp[i][j - 1]][j - 1];
            }
        }
    }

    // LCA 구하기
    static int getLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        int diff = depth[u] - depth[v];
        for (int i = 0; diff > 0; i++) {
            if ((diff & 1) == 1) u = dp[u][i];
            diff >>= 1;
        }
        if (u != v) {
            for (int i = 17; i >= 0; i--) {
                if (dp[u][i] != dp[v][i]) {
                    u = dp[u][i];
                    v = dp[v][i];
                }
            }
            u = dp[u][0];
        }
        return u;
    }

    // 쿼리 입력 및 사전 처리
    static void processQueries() {
        q = sc.nextInt();
        for (int i = 0; i < q; i++) {
            int s = sc.nextInt(), e = sc.nextInt();
            queries[i] = new Query();
            queries[i].s = s;
            queries[i].e = e;
            queries[i].i = i;
            if (in[s] > in[e]) {
                int tmp = s;
                s = e;
                e = tmp;
            }
            int lca = getLCA(s, e);
            queries[i].e = in[e];
            if (s == lca) {
                queries[i].s = in[s];
            } else {
                queries[i].s = out[s];
                queries[i].k = in[lca];
            }
        }
        Arrays.sort(queries, 0, q, (a, b) -> {
            int blockSize = 400;
            if (a.s / blockSize != b.s / blockSize) return a.s / blockSize - b.s / blockSize;
            return a.e - b.e;
        });
    }

    // 쿼리의 구간에 따라 노드 추가/삭제
    static void toggle(int node, int add) {
        int val = weight[node];
        nodeCount[node] += add;
        if (nodeCount[node] == 1) {
            weightCount[val]++;
            if (weightCount[val] == 1) t++;
        }
        if (nodeCount[node] == (add > 0 ? 2 : 0)) {
            weightCount[val]--;
            if (weightCount[val] == 0) t--;
        }
    }

    // Mo's 알고리즘 수행
    static void solve(int s, int e) {
        for (int i = 0; i < q; i++) {
            while (s > queries[i].s) toggle(rec[--s], 1);
            while (s < queries[i].s) toggle(rec[s++], -1);
            while (e > queries[i].e) toggle(rec[e--], -1);
            while (e < queries[i].e) toggle(rec[++e], 1);
            if (queries[i].k != 0) toggle(rec[queries[i].k], 1);
            answer[queries[i].i] = t;
            if (queries[i].k != 0) toggle(rec[queries[i].k], -1);
        }
        for (int i = 0; i < q; i++) {
            System.out.println(answer[i]);
        }
    }
}
