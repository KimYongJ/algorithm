import java.io.*;
import java.util.*;

public class Main {
    // 최대 노드 수 상수
    static final int N = 100001;

    // 입력으로 주어질 트리의 노드 개수 n, 쿼리 개수 m
    static int n, m;
    
    // 그래프 인접 리스트: graph[u]에 (v, w) 쌍 저장
    static ArrayList<int[]>[] graph = new ArrayList[N];
    
    // 간선 정보 저장: edge[i] = {u, v}
    static int[][] edge = new int[N][];
    
    // 세그먼트 트리 배열: 크기 4*n
    static int[] segTree = new int[N * 4];

    // 서브트리 크기 배열
    static int[] sz = new int[N];
    // HLD용: 노드 -> 세그먼트 인덱스
    static int[] HLD_num = new int[N];
    // HLD용: 노드 깊이 (체인 레벨)
    static int[] chain_lv = new int[N];
    // HLD용: 각 체인의 head 노드
    static int[] chain_head = new int[N];
    // HLD용: 체인별 부모 체인 상위 노드
    static int[] chain_par = new int[N];

    // 세그먼트 트리 포인트 업데이트
    static void update(int ptr, int left, int right, int idx, int val) {
        // 1) 업데이트 위치가 현재 구간 [left..right] 밖이면 무시
        if (idx < left || idx > right) return;
        
        // 2) 리프 노드 도달: 값을 val로 설정
        if (left == right) {
            segTree[ptr] = val;
            return;
        }
        
        int mid = (left + right) >> 1;
        // 3) 좌우 자식 재귀 호출
        update(ptr * 2, left, mid, idx, val);
        update(ptr * 2 + 1, mid + 1, right, idx, val);
        
        // 4) 부모 노드는 두 자식의 최대값
        segTree[ptr] = Math.max(segTree[ptr * 2], segTree[ptr * 2 + 1]);
    }

    // 세그먼트 트리 범위 최대값 쿼리 [ql..qr]
    static int getVal(int ptr, int left, int right, int ql, int qr) {
        // 1) 쿼리 구간이 현재 노드 범위와 겹치지 않으면 0 반환
        if (qr < left || right < ql) return 0;
        // 2) 완전 포함되면 해당 노드 값 반환
        if (ql <= left && right <= qr) return segTree[ptr];
        
        int mid = (left + right) >> 1;
        // 3) 좌우 자식에서 가져와 최대값 반환
        return Math.max(
            getVal(ptr * 2, left, mid, ql, qr),
            getVal(ptr * 2 + 1, mid + 1, right, ql, qr)
        );
    }

    // 1) 서브트리 크기 계산 및 heavy child를 그래프 순서 앞에 배치
    static void getSize(int v, int p) {
        sz[v] = 1;  // 자기자신 포함
        for (int i = 0; i < graph[v].size(); i++) {
            int[] nxt = graph[v].get(i);  // {nv, w}
            int nv = nxt[0];
            if (nv == p) continue;  // 부모는 건너뛰기

            // 자식 서브트리 순회
            getSize(nv, v);
            sz[v] += sz[nv];  // 서브트리 크기 합산

            // heavy child(최대 sz)를 첫 번째 위치로 스왑
            int front = graph[v].get(0)[0];
            if (sz[nv] > sz[front] || front == p) {
                Collections.swap(graph[v], 0, i);
            }
        }
    }

    // HLD DFS: 체인 인덱스, 레벨, head, parent 관리
    static int HLD_cnt = 1;
    static void DFS(int v, int p, int lvl) {
        // 노드 v에 세그번호 할당
        HLD_num[v] = HLD_cnt++;
        // 체인 레벨 저장
        chain_lv[v] = lvl;

        boolean isFirst = true;  // 첫 번째 자식은 same chain
        for (int[] nxt : graph[v]) {
            int nv = nxt[0];
            int w  = nxt[1];
            if (nv == p) continue;

            if (isFirst) {
                // heavy child: same chain head 유지
                chain_head[nv] = chain_head[v];
                chain_par[nv]  = chain_par[v];
                DFS(nv, v, lvl);
                isFirst = false;
            } else {
                // light child: 새 체인 시작
                chain_head[nv] = nv;
                chain_par[nv]  = v;
                DFS(nv, v, lvl + 1);
            }
            // 연결된 간선 가중치로 세그 업데이트
            update(1, 1, n, HLD_num[nv], w);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // n: 노드 수
        n = Integer.parseInt(br.readLine());
        // 그래프 초기화
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        // 간선 입력
        for (int i = 1; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, w});
            edge[i] = new int[]{u, v};
        }

        // 1) 서브트리 크기 및 heavy child 선정
        getSize(1, 0);

        // 2) HLD 초기 설정: root 체인 head 자기 자신
        chain_head[1] = 1;
        chain_par[1]  = 0;  // 루트 체인에 부모는 없음

        // 3) HLD DFS 시작: 세그번호, 체인 분할, 세그 업데이트
        DFS(1, 0, 1);

        // 쿼리 개수 입력
        m = Integer.parseInt(br.readLine());
        while (m-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            if (op == 1) {
                // update: 간선 i의 가중치 c 로 변경
                int i = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int u = edge[i][0], v = edge[i][1];
                // 부모 관계에 맞춰 v가 자식
                if (HLD_num[u] > HLD_num[v]) { int t=u; u=v; v=t; }
                update(1, 1, n, HLD_num[v], c);
            } else {
                // query: u, v 경로 최대 간선 가중치
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                // 깊이 맞춤 (u가 상위)
                if (chain_lv[u] > chain_lv[v]) { int t=u; u=v; v=t; }

                int ans = 0;
                // 서로 다른 체인에서 올라가기
                while (chain_lv[u] < chain_lv[v]) {
                    ans = Math.max(ans,
                        getVal(1, 1, n, HLD_num[chain_head[v]], HLD_num[v]));
                    v = chain_par[v];
                }
                // 같은 체인 내에서
                while (chain_head[u] != chain_head[v]) {
                    ans = Math.max(ans,
                        getVal(1, 1, n, HLD_num[chain_head[u]], HLD_num[u]));
                    ans = Math.max(ans,
                        getVal(1, 1, n, HLD_num[chain_head[v]], HLD_num[v]));
                    u = chain_par[u];
                    v = chain_par[v];
                }
                // 최종 구간 (exclude 공통 조상)
                if (HLD_num[u] > HLD_num[v]) { int t=u; u=v; v=t; }
                ans = Math.max(ans,
                    getVal(1, 1, n, HLD_num[u] + 1, HLD_num[v]));

                System.out.println(ans);
            }
        }
    }
}
