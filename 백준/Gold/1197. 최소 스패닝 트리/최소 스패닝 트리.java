//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

class Node{
    int a, b;
    int dist;
    Node(int a, int b, int dist){
        this.a = a;
        this.b = b;
        this.dist = dist;
    }
}

class Main{
    
    static int V, E;
    static long RESULT;
    static int a, b, d;
    static int parent[];
    static PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->{
    	if(a.dist <= b.dist) {
    		return -1;
    	}
    	return 1;
    });
    
    public static void changeParent(int a, int b){
        parent[a] = b;
    }
    
    // 부모노드 가져오는 함수
    public static int getParent(int x){
        if(parent[x] == x) return x;
        return getParent(parent[x]);
    }
    
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        parent = new int[V+1];
        
        for(int i=1; i<=V; i++)
        	parent[i] = i;
        
        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            pq.add(new Node(a,b,d));
        }
        
        while(V>1){
            Node now = pq.poll();
            int aParent = getParent(now.a);
            int bParent = getParent(now.b);
            if(aParent != bParent){
                V--;
                RESULT += now.dist;
                if(aParent > bParent){
                    changeParent(aParent, bParent);
                }else changeParent(bParent, aParent);
            }
        }
        
        System.out.println(RESULT);
    }
}