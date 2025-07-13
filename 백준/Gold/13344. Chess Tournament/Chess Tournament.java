// https://www.acmicpc.net/problem/13344
// 5초 512MB

import java.io.*;
import java.util.*;

class Main {

    static int N, M;
    static int[] parent;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 2 ≤ N ≤ 50 000
        M = Integer.parseInt(st.nextToken());   // 1 ≤ M ≤ 250 000

        parent = new int[N];
        for (int i = 0; i < N; i++) parent[i] = i;

        // 간선 정보 임시 저장
        int[] from = new int[M];
        int[] to   = new int[M];
        int edgeCnt = 0;

        // 1) 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            int b = Integer.parseInt(st.nextToken());

            if (c == '=') {             // 동급 → 합집합
                union(a, b);
            } else {                    // ‘>’
                from[edgeCnt] = a;
                to[edgeCnt]   = b;
                edgeCnt++;
            }
        }

        // 2) 간선 정규화(대표 노드 기준) & 오류 체크
        List<Integer>[] adj = new ArrayList[N];
        int[] indeg = new int[N];

        for (int i = 0; i < edgeCnt; i++) {
            int u = find(from[i]);
            int v = find(to[i]);
            if (u == v) {               // 같은 집합에서 u > v ⇒ 모순
                System.out.print("inconsistent");
                return;
            }
            if (adj[u] == null) adj[u] = new ArrayList<>();
            adj[u].add(v);
            indeg[v]++;
        }

        // 3) Kahn 위상 정렬로 사이클 여부 판별
        Deque<Integer> dq = new ArrayDeque<>();
        int reps = 0;                   // 대표 노드 수
        for (int i = 0; i < N; i++) {
            if (parent[i] == i) {       // 대표 노드만 세면 충분
                reps++;
                if (indeg[i] == 0) dq.add(i);
            }
        }

        int processed = 0;
        while (!dq.isEmpty()) {
            int cur = dq.poll();
            processed++;
            if (adj[cur] == null) continue;
            for (int nxt : adj[cur]) {
                if (--indeg[nxt] == 0) dq.add(nxt);
            }
        }

        System.out.print(processed == reps ? "consistent"
                                           : "inconsistent");
    }

    /* ---------- Union–Find ---------- */
    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    static void union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa != pb) parent[pb] = pa;
    }
}
