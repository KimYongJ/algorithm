// https://github.com/KimYongJ/algorithm
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
    
    // 빠른 입력을 위해 만든 함수
    public static int read() throws Exception {
		int c, n = System.in.read() & 15;
		boolean isNegative = n == 13;
		if (isNegative) n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13) System.in.read();
		return isNegative ? ~n + 1 : n;
	}
    
    // 부모의 부모를 바꾸는 함수
    public static void changeParent(int a, int b){
        parent[a] = b;
    }
    
    // 부모노드 가져오는 함수
    public static int getParent(int x){
        if(parent[x] == x) return x;
        return getParent(parent[x]);
    }
    
    public static void main(String[] args)throws Exception{ 
        V 				= read();
        E 				= read();
        parent = new int[V+1];
        
        for(int i=1; i<=V; i++)
        	parent[i] 	= i;
        
        for(int i=0; i<E; i++){
            a 			= read();
            b 			= read();
            d 			= read();
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