import java.io.*;
import java.util.*;

public class Main {

    static final long MOD = 1_000_000_007L;  // 10^9+7

    static int N, Q, idx = 0;
    static long prevX, prevY;

    static int[] query, segIdx, chainLevel, chainHeader, chainParent;
    static long[] bit;
    static List<Integer>[] g;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int SZ = N + 2;                 // 안전 여유 1
        query       = new int[9];
        segIdx      = new int[SZ];
        chainLevel  = new int[SZ];
        chainHeader = new int[SZ];
        chainParent = new int[SZ];
        bit         = new long[SZ];
        g           = new ArrayList[SZ];
        for (int i = 0; i < SZ; i++) g[i] = new ArrayList<>();
        chainHeader[1] = 1;

        /* -------- 트리 입력 -------- */
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) + 1;
            int b = Integer.parseInt(st.nextToken()) + 1;
            g[a].add(b);
            g[b].add(a);
        }

        /* -------- HLD 전처리 -------- */
        setChild(1, 0, new int[SZ]);
        setHLD(1, 1);

        /* -------- 초기 화폐 -------- */
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) add(segIdx[i], Long.parseLong(st.nextToken()));

        /* -------- 쿼리 처리 -------- */
        Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 9; i++) query[i] = Integer.parseInt(st.nextToken());

            for (int i = 0; i < query[0]; i++) {
                int node = (int) getX(i) + 1;   // 1..N
                add(segIdx[node], getY(i));
            }
            sb.append(pathSum(query[7] + 1, query[8] + 1)).append('\n');
        }
        System.out.print(sb);
    }

    /* ---------- x(i), y(i) ---------- */
    static long getX(int i) {
        if (i == 0) prevX = query[1] % N;
        else        prevX = ((long) query[3] * prevX + query[4]) % N;
        return prevX;
    }
    static long getY(int i) {
        if (i == 0) prevY = query[2];
        else        prevY = ((long) query[5] * prevY + query[6]) % MOD;
        return prevY;
    }

    /* ---------- Fenwick ---------- */
    static void add(int i, long v) {
        while (i <= N) {
            bit[i] += v;
            i += i & -i;
        }
    }
    static long sum(int i) {
        long s = 0;
        while (i > 0) {
            s += bit[i];
            i -= i & -i;
        }
        return s;
    }

    /* ---------- HLD 쿼리 ---------- */
    static long pathSum(int u, int v) {
        long res = 0;
        while (chainHeader[u] != chainHeader[v]) {
            if (chainLevel[chainHeader[u]] < chainLevel[chainHeader[v]]) {
                int t = u; u = v; v = t;
            }
            res += sum(segIdx[u]) - sum(segIdx[chainHeader[u]] - 1);
            u = chainParent[chainHeader[u]];
        }
        if (segIdx[u] > segIdx[v]) { int t = u; u = v; v = t; }
        return res + sum(segIdx[v]) - sum(segIdx[u] - 1);
    }

    /* ---------- HLD Build ---------- */
    static void setChild(int cur, int par, int[] sz) {
        sz[cur] = 1;
        int heavyIdx = -1, heavySize = 0;
        for (int i = 0; i < g[cur].size(); i++) {
            int nxt = g[cur].get(i);
            if (nxt == par) continue;
            setChild(nxt, cur, sz);
            sz[cur] += sz[nxt];
            if (sz[nxt] > heavySize) { heavySize = sz[nxt]; heavyIdx = i; }
        }
        if (heavyIdx >= 0) Collections.swap(g[cur], 0, heavyIdx);
    }
    static void setHLD(int cur, int lvl) {
        chainLevel[cur] = lvl;
        segIdx[cur]     = ++idx;
        for (int i = 0; i < g[cur].size(); i++) {
            int nxt = g[cur].get(i);
            if (segIdx[nxt] != 0) continue;          // 방문済
            if (i == 0) {                            // heavy
                chainHeader[nxt] = chainHeader[cur];
                chainParent[nxt] = chainParent[cur];
                setHLD(nxt, lvl);
            } else {                                 // light
                chainHeader[nxt] = nxt;
                chainParent[nxt] = cur;
                setHLD(nxt, lvl + 1);
            }
        }
    }
}
