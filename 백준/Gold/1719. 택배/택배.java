// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;

class Main{
    static final int INF = 200_000; // 최대로 나올 수 있는 거리
    static int N, M, dist[], first[];
    static ArrayList<Node>[] list; // 인접리스트를 담을 list 배열
    static PriorityQueue<Node> q; // 우선순위큐(dist기준 오름차순)
    public static void main(String[] args)throws Exception{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken())+1;
        M = Integer.parseInt(st.nextToken());
        
        list = new ArrayList[N];
        
        for(int i=1; i<N; i++)
            list[i] = new ArrayList<Node>();
        
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            
            list[a].add(new Node(b,d,0)); // 양방향 셋팅
            list[b].add(new Node(a,d,0)); // 양방향 셋팅
        }
        
        StringBuilder sb = new StringBuilder();
        
        for(int i=1; i<N; i++)
            Dijkstra(i,sb); // 노드마다 다익스트라 알고리즘 실행
        
        System.out.println(sb);
    }
    public static void Dijkstra(int start, StringBuilder sb){
        q = new PriorityQueue<Node>((a,b)->a.dist-b.dist); // 우선순위 큐, dist기준 오름 차순 정렬
        dist = new int[N]; // start노드의 최단거리를 담을 배열
        first = new int[N];// 처음 방문한 노드를 담을 배열
        
        Arrays.fill(dist,INF);
        
        dist[start] = 0;
        q.add(new Node(start,0,-1));
        
        while(!q.isEmpty()){
            Node node = q.poll();
            int nowNode = node.node;
            int nowDist = node.dist;
            
            for(int i=0; i<list[nowNode].size(); i++){
                Node node2 = list[nowNode].get(i);
                int nextNode = node2.node;
                int nextDist = node2.dist;
                
                int distSum = nowDist + nextDist;
                
                if(dist[nextNode] > distSum){ // 최단거리 갱신 조건
                    dist[nextNode] = distSum;
                    
                    int firstNode = node.first; // 노드의 첫 방문 
                    if(node.first==-1){ // node.first가 -1이면 , 즉 첫 방문이면 nextNode가 첫방문 노드가됨.
                        firstNode = nextNode;
                    }
                    first[nextNode] = firstNode;
                    q.add(new Node(nextNode, distSum, firstNode));
                }
                
            }
            
        }
        for(int i=1; i<N; i++){
            if(i==start) sb.append("- ");
            else sb.append(first[i]).append(' ');
        }
        sb.append('\n');
    }
}
class Node{
    int node, dist, first;
    Node(int node, int dist, int first){
        this.node = node;
        this.dist = dist;
        this.first = first; // 첫 방문 노드를 담을 변수
    }
}