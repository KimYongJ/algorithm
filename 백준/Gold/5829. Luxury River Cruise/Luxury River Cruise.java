import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node {
    int node;
    Node next;
    Node(int node, Node next) {
        this.node = node;
        this.next = next;
    }
}

class Main {
    static int N, M, K, tree[][], endPoint[], direct[], dist[];
    static int cycleStartNode, cycleDist, cycleLength;
    static Node adNode[];
    static boolean visit[];
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        tree = new int[N+1][2];
        endPoint = new int[N+1];
        direct = new int[M];
        adNode = new Node[N+1];
        visit = new boolean[N+1];
        dist = new int[N+1];
        
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            tree[i][0] = Integer.parseInt(st.nextToken());
            tree[i][1] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            direct[i] = st.nextToken().charAt(0) == 'L' ? 0 : 1;
        }
        
        for(int i=1; i<=N; i++) {
            make_adNode_DFS(i, i, 0);
        }
        
        // dist 배열 초기화
        for(int i=1; i<=N; i++) {
            dist[i] = -1;
        }
        
        find_cycle_DFS(1, 0);
        
        if(K < cycleDist) {
            // 사이클에 도달하기 전에 K번째 위치를 찾는 경우
            DFS(1, 0);
        } else {
            // 사이클에 도달한 후의 위치를 찾는 경우
            K -= cycleDist;
            K %= cycleLength;
            DFS(cycleStartNode, 0);
        }
    }
    
    public static void find_cycle_DFS(int nowNode, int depth) {
        if(dist[nowNode] != -1) {
            // 사이클 발견
            cycleStartNode = nowNode;
            cycleDist = dist[nowNode];
            cycleLength = depth - dist[nowNode];
            return;
        }
        
        if(depth == K) {
            System.out.print(nowNode);
            System.exit(0);
        }
        
        dist[nowNode] = depth;
        find_cycle_DFS(adNode[nowNode].node, depth + 1);
    }
    
    public static void DFS(int nowNode, int depth) {
        if(depth == K) {
            System.out.print(nowNode);
            return;
        }
        DFS(adNode[nowNode].node, depth + 1);
    }
    
    public static void make_adNode_DFS(int startNode, int nowNode, int depth) {
        if(depth == M) {
            adNode[startNode] = new Node(nowNode, adNode[startNode]);
            return;
        }
        int nextNode = tree[nowNode][direct[depth]];
        make_adNode_DFS(startNode, nextNode, depth + 1);
    }
}