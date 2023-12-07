// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.ArrayDeque;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int result = -1;
        int N = Integer.parseInt(br.readLine());
        
        ArrayList<Integer>[] adList = new ArrayList[N+1]; // 인접리스트 생성
        for(int i=1; i<=N; i++) // 인접 리스트 초기화
            adList[i] = new ArrayList<>();
        
        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(br.readLine());
        
        for(int i=0; i<P; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adList[a].add(b);
            adList[b].add(a);
        }
        
        ArrayDeque<Node> q = new ArrayDeque<Node>(){{add(new Node(A,0));}};
        boolean visit[] = new boolean[N+1];
        Loop:
        while(!q.isEmpty()){
            Node now = q.poll();
            int nowNode = now.node;
            int nowDist = now.dist;
            
            if(visit[nowNode]) continue; // 방문했던 노드는 스킵
            visit[nowNode] = true;
            
            for(int i=0; i<adList[nowNode].size(); i++){
                int nextNode = adList[nowNode].get(i);
                int nextDist = nowDist + 1;
                if(nextNode == B){
                    result = nextDist;
                    break Loop;
                }
                q.add(new Node(nextNode, nextDist));
            }
            
        }
        System.out.println(result);
    }
    
}
class Node{
    int node, dist;
    Node(int node, int dist){
        this.node = node;
        this.dist = dist;
    }
}