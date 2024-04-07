// https://github.com/KimYongJ/algorithm
import java.util.ArrayDeque;
import java.util.ArrayList;
class Node{
    int node, cnt;
    Node(int node, int cnt){this.node=node; this.cnt=cnt;}
}
class Main{
    
    static int start, end, N, M;
    static boolean visit[];
    static ArrayList<Integer>[] adlist;
    static ArrayDeque<Node> q;
    static int read() throws Exception {// 빠른 입력을 위한 함수
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
    public static void BFS(){
    	int cnt = -1;
        visit[start] = true;
        q.add(new Node(start,0));
        
        while(!q.isEmpty())
        {
            Node now = q.poll();
            
            if(now.node == end)
            {
                cnt = now.cnt;
                break;
            }
            
            for(int next : adlist[now.node])
                if(!visit[next]){
                    visit[next] = true;
                    q.add(new Node(next, now.cnt+1));
                }
        }
        System.out.println(cnt);
    }
    public static void main(String[] args)throws Exception{
        start 	= read();
        end 	= read();
        N 		= read();
        M 		= read();
        q 		= new ArrayDeque<>();
        adlist 	= new ArrayList[N+1];
        visit 	= new boolean[N+1];
        
        for(int i=0; i<=N; i++)
            adlist[i] = new ArrayList<>();
        int a,b;
        for(int i=0; i<M; i++)
        {
            a = read();
            b = read();
            adlist[a].add(b);
            adlist[b].add(a);
        }
        
        BFS();
    }
}