// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
    
    public static void main(String[] args)throws Exception{
    	final int INF = 5_000_001;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Node[] node = new Node[M];
        long[] dist = new long[N+1];
        Arrays.fill(dist,INF);
        
        dist[1] = 0;
        
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            node[i] = new Node(a,b,d);
        }
        // 노드 n-1만큼 만복하여 노드의 최단거리를 구한다.
        for(int i=0; i<N-1; i++){
            for(int j=0; j<M; j++){
                int start = node[j].start;
                int end = node[j].end;
                int nowdist = node[j].dist;
                
                // 벨만 포드는 한번이라도 계산되었던 정점만 탐색을 해야 한다. 그렇기에 들어가는 코드이다. 
                if(dist[start] == INF) continue;
                
                if(dist[end] > dist[start] + nowdist){
                    dist[end] = dist[start] + nowdist;
                }
            }
        }
        
        // 음의 간선이 있는지 체크 
        for(int j=0; j<M; j++){
            int start = node[j].start;
            int end = node[j].end;
            int nowdist = node[j].dist;
         // 벨만 포드는 한번이라도 계산되었던 정점만 탐색을 해야 한다. 그렇기에 들어가는 코드이다. 
            if(dist[start] == INF) continue;
            // 갱신 되는 부분이 있다면 음의 간선이 있다는 말
            if(dist[end] > dist[start] + nowdist){
                System.out.println(-1); 
                return;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=2; i<=N; i++){
        	long num = dist[i];
            if(num==INF) num = -1;
            sb.append(num).append('\n');
        }
        System.out.println(sb);
    }
}
class Node{
    int start, end, dist;
    Node(int start, int end, int dist){
        this.start = start;
        this.end = end;
        this.dist = dist;
    }
}