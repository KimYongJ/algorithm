import java.io.*;
import java.util.*;

public class Main {

    static int N, Q;
    static ArrayList<Integer>[] adj;
    static int[] parent, depth, size, heavy, head;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        /* ---------- 입력 & 트리 구성 ---------- */
        N = Integer.parseInt(br.readLine());
        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }

        /* ---------- 전처리 ①: 서브트리 크기 & heavy child ---------- */
        parent = new int[N + 1];
        depth  = new int[N + 1];
        size   = new int[N + 1];
        heavy  = new int[N + 1];
        Arrays.fill(heavy, -1);

        dfs(1, 0);          // root = 1

        /* ---------- 전처리 ②: 체인(head) 배정 ---------- */
        head = new int[N + 1];
        decompose(1, 1);    // (현재 노드, 체인 head)

        /* ---------- 질의 처리 ---------- */
        Q = Integer.parseInt(br.readLine());
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            sb.append(lca(u, v)).append('\n');
        }
        System.out.print(sb);
    }

    /* 서브트리 크기 계산 + heavy child 선택 */
    static void dfs(int v, int p) {
        parent[v] = p;
        size[v]   = 1;
        for (int u : adj[v]) {
            if (u == p) continue;
            depth[u] = depth[v] + 1;
            dfs(u, v);
            size[v] += size[u];
            if (heavy[v] == -1 || size[u] > size[heavy[v]]) heavy[v] = u;
        }
    }

    /* Heavy 경로는 같은 head, Light 경로는 새 head */
    static void decompose(int v, int h) {
        head[v] = h;
        if (heavy[v] != -1)                 // heavy 간선 먼저 따라감
            decompose(heavy[v], h);
        for (int u : adj[v]) {              // 나머지는 light
            if (u == parent[v] || u == heavy[v]) continue;
            decompose(u, u);                // 새 체인 시작 → head = 자기 자신
        }
    }

    /* HLD 기반 LCA: 체인이 같아질 때까지 ‘더 깊은 체인의 head’를 한 번에 올림 */
    static int lca(int u, int v) {
        while (head[u] != head[v]) {
            if (depth[head[u]] < depth[head[v]]) {
                v = parent[head[v]];
            } else {
                u = parent[head[u]];
            }
        }
        return (depth[u] < depth[v]) ? u : v;
    }
}
