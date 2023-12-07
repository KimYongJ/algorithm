// https://github.com/KimYongJ/algorithm
import java.util.ArrayDeque;
import java.util.ArrayList;
class Main{
    public static void main(String[] args)throws Exception{
        int result = -1;
        int N = read();
        
        ArrayList<Integer>[] adList = new ArrayList[N+1]; // 인접리스트 생성
        for(int i=1; i<=N; i++) // 인접 리스트 초기화
            adList[i] = new ArrayList<>();
        
        int A = read();
        int B = read();
        int P = read();
        
        for(int i=0; i<P; i++){
            int a = read();
            int b = read();
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
    // 빠른 입력을 위해 만든 함수
    public static int read() throws Exception {
		int c, n = System.in.read() & 15;
		boolean isNegative = n == 13;
		if (isNegative) n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13) System.in.read();
		return isNegative ? ~n + 1 : n;
	}
    
}
class Node{
    int node, dist;
    Node(int node, int dist){
        this.node = node;
        this.dist = dist;
    }
}