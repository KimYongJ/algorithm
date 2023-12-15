// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

class Node{
    int a, b, dist;
    Node(int a, int b, int dist){
        this.a = a;
        this.b = b;
        this.dist = dist;
    }
}

class Main{
    
    public static int getParent(int x, int[] parent){// 부모 노드를 찾는 함수
        if(parent[x] == x) return x; // 자기 자신이 부모노드이면 반환 후 종료
        return getParent(parent[x], parent); // 자기 자신이 부모노드가 아니면 부모님 찾아 재귀 실행
    }
    public static void changeAtoB(int A, int B, int[] parent) {
    	for(int i=0; i<parent.length; i++) {
    		if(parent[i] == A) {
    			parent[i] = B;
    		}
    	}
    }
    public static void main(String[] args)throws Exception{
    	PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->a.dist-b.dist);
    	int N, M, result=0;
        int a, b, d;
        int parent[];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parent = new int[N+1]; // 부모 노드를 담을 배열 
        for(int i=1; i<=N; i++)
            parent[i] = i; // 부모 노드를 자기 자신으로 초기화
        
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            pq.add(new Node(a,b,d));
        }
        while(!pq.isEmpty()){
            Node now = pq.poll();
            int aParent = getParent(now.a, parent);
            int bParent = getParent(now.b, parent);
            if(aParent > bParent){
                result += now.dist;
                changeAtoB(parent[now.a], bParent, parent);
            }else if(bParent > aParent){
                result += now.dist;
                changeAtoB(parent[now.b], aParent, parent);
            }
            
        }
        
        System.out.println(result);
    }
}