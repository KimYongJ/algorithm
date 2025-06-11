import java.io.*;
import java.util.*;

/**
 * 트리의 흰색 정점 쌍 LCA 레벨 합
 *  - Heavy-Light Decomposition + 구간 덧셈 / 구간 합 세그먼트 트리
 */
public class Main {

    /* ---------- 문제 상수 ---------- */
    static final int MAX = 500_000;          // N, Q ≤ 5e5

    /* ---------- 입력 데이터 ---------- */
    static int n, q;
    static int[] color  = new int[MAX + 1];  // a[i] : 0(검) / 1(흰)
    static int[] parent = new int[MAX + 1];  // P[i] : 부모
    static List<Integer>[] tree = new ArrayList[MAX + 1];

    /* ---------- HLD / 세그먼트 트리용 배열 ---------- */
    static int[] sub = new int[MAX + 1];     // 서브트리 크기
    static long[] dp = new long[MAX + 1];    // 흰색 개수(자기 서브트리)
    static int[] in  = new int[MAX + 1];     // Euler tour in
    static int[] out = new int[MAX + 1];     // Euler tour out
    static int[] top = new int[MAX + 1];     // 체인 top
    static int[] depth = new int[MAX + 1];   // 깊이
    static int timer = 0;

    /* ---------- 세그먼트 트리 ---------- */
    static class Seg {
        final long[] sum, lazy;
        final int n;

        Seg(int n) {
            this.n = n;
            sum  = new long[n * 4];
            lazy = new long[n * 4];
        }

        void push(int node, int s, int e) {
            if (lazy[node] != 0) {
                sum[node] += lazy[node] * (e - s + 1);
                if (s != e) {
                    lazy[node << 1]     += lazy[node];
                    lazy[node << 1 | 1] += lazy[node];
                }
                lazy[node] = 0;
            }
        }

        void update(int node, int s, int e, int l, int r, long v) {
            push(node, s, e);
            if (e < l || r < s) return;
            if (l <= s && e <= r) {
                lazy[node] += v;
                push(node, s, e);
                return;
            }
            int m = (s + e) >>> 1;
            update(node << 1,     s, m, l, r, v);
            update(node << 1 | 1, m + 1, e, l, r, v);
            sum[node] = sum[node << 1] + sum[node << 1 | 1];
        }

        long query(int node, int s, int e, int l, int r) {
            push(node, s, e);
            if (e < l || r < s) return 0;
            if (l <= s && e <= r) return sum[node];
            int m = (s + e) >>> 1;
            return query(node << 1, s, m, l, r) +
                   query(node << 1 | 1, m + 1, e, l, r);
        }
    }
    static Seg seg;          // 전역 세그먼트 트리

    /* ---------- 1단계: 서브트리 크기·dp·heavy child 선정 ---------- */
    static void dfs0(int v) {
        sub[v] = 1;
        dp[v]  = color[v];
        for (int i : tree[v]) {
            depth[i] = depth[v] + 1;
            dfs0(i);
            sub[v] += sub[i];
            dp[v]  += dp[i];
        }
        if (tree[v].isEmpty()) return;
        // 가장 큰 자식을 vec[v][0]으로(heavy child)
        for (int i = 1; i < tree[v].size(); ++i)
            if (sub[tree[v].get(0)] < sub[tree[v].get(i)])
                Collections.swap(tree[v], 0, i);
    }

    /* ---------- 2단계: 체인 top & Euler 인덱스 ---------- */
    static void dfs(int v) {
        in[v] = ++timer;
        for (int i : tree[v]) {
            top[i] = (i == tree[v].get(0)) ? top[v] : i;
            dfs(i);
        }
        out[v] = timer;
    }

    /* ---------- HLD path 연산 ---------- */
    static void pathUpdate(int v, long delta) {
        while (v != 0) {
            seg.update(1, 1, timer, in[top[v]], in[v], delta);
            v = parent[top[v]];
        }
    }

    static long pathQuery(int v) {
        long res = 0;
        while (v != 0) {
            res += seg.query(1, 1, timer, in[top[v]], in[v]);
            v = parent[top[v]];
        }
        // 루트(1) 값은 한 번 빼기
        res -= seg.query(1, 1, timer, in[1], in[1]);
        return res;
    }

    /* ---------- 메인 ---------- */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /* 입력 */
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; ++i) tree[i] = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; ++i) color[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 2; i <= n; ++i) {
            parent[i] = Integer.parseInt(st.nextToken());
            tree[parent[i]].add(i);
        }

        /* 전처리 */
        top[1] = 1;
        dfs0(1);
        dfs(1);
        seg = new Seg(timer);

        /* 초기 답 계산 */
        long ans = 0;
        for (int i = 1; i <= n; ++i) {
            long sum1 = 0, sum2 = 0;
            for (int ch : tree[i]) {
                sum1 += dp[ch];
                sum2 += dp[ch] * dp[ch];
            }
            ans += (long) depth[i] * (sum1 * sum1 - sum2) / 2;
            if (color[i] == 1) ans += (long) depth[i] * sum1;
        }

        /* 세그먼트 트리에 초기 dp 삽입 */
        for (int i = 1; i <= n; ++i)
            seg.update(1, 1, timer, in[i], in[i], dp[i]);

        StringBuilder sb = new StringBuilder();
        sb.append(ans).append('\n');

        /* 쿼리 처리 */
        while (q-- > 0) {
            int x = Integer.parseInt(br.readLine().trim());
            if (color[x] == 0) {          // 검 → 흰
                color[x] = 1;
                ans += pathQuery(x);
                pathUpdate(x, 1);
            } else {                      // 흰 → 검
                color[x] = 0;
                ans -= pathQuery(x) - depth[x];
                pathUpdate(x, -1);
            }
            sb.append(ans).append('\n');
        }
        System.out.print(sb);
    }
}
