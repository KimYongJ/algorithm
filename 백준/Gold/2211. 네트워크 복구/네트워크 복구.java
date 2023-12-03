// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;
class Main{
    
    static int N, M; 
    static DIST dist[]; // 1번까지 최단거리와 연결과정에서 노드들을 담을 DIST객체 배열
    static ArrayList<Node>[] list;// 인접리스트
    static final int INF = 9_999;
    public static void main(String[] args)throws Exception{
        StringBuilder sb = new StringBuilder();
        N = read()+1;
        M = read();
        list = new ArrayList[N];
        dist = new DIST[N];
        for(int i=1; i<N; i++){
            list[i] = new ArrayList<Node>();
            dist[i] = new DIST(INF,-1);
        }
        
        for(int i=0; i<M; i++){
            int a = read();
            int b = read();
            int d = read();
            list[a].add(new Node(b,d));
            list[b].add(new Node(a,d));
        }
        if(N==2) {// 노드가 1개일 경우
        	sb.append(1).append('\n');
        	sb.append(1).append(' ').append(1);
        }else {
        	Dijkstra();
        	sb.append(N-2).append('\n');
        	for(int i=2; i<N; i++) {
        		int end = dist[i].adjacent_vertex;
        		sb.append(i).append(' ').append(end);
        		sb.append('\n');
        	}
        }
        System.out.println(sb);
    }
    
    public static void Dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->a.dist-b.dist);
        dist[1].dist = 0;
        pq.add(new Node(1,0));
        
        while(!pq.isEmpty()){
            Node now = pq.poll();
            int nowNode = now.node; // 현재 노드
            int until_now_dist = now.dist;// 현재 노드까지 오는데 걸리는 거리
            
            for(int i=0; i<list[nowNode].size(); i++){
                Node next = list[nowNode].get(i);
                int nextNode = next.node;
                int nextDist = next.dist;
                
                int distSum = until_now_dist + nextDist;
                
                if(dist[nextNode].dist > distSum){
                    dist[nextNode].dist = distSum;
                    pq.add(new Node(nextNode, distSum));
                    dist[nextNode].adjacent_vertex = nowNode;
                    
                }
            }
            
        }
        
    }
    static int read() throws Exception { // 빠른 입력을 위한 함수
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

}
class DIST{
    int dist;
    int adjacent_vertex;
    DIST(int dist, int adjacent_vertex){
        this.dist = dist;
        this.adjacent_vertex = adjacent_vertex;
    }
}
class Node{
    int node, dist;
    Node(int node, int dist){
        this.node = node;
        this.dist = dist;
    }
}