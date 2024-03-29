// https://github.com/KimYongJ/algorithm

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
	static PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->a.dist-b.dist);
	static int N, M, cnt=1, result;
	static int a, b, d;
	static int parent[];
	
	// 빠른 입력을 위한 함수
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }

	public static void changeParent(int a, int b, int dist) {
		parent[a] 		= b;							// 부모 노드를 변경
		result 			+= dist;						// 결과 추가
	}
    public static int getParent(int x, int[] parent){	// 부모 노드를 찾는 함수
        if(parent[x] == x) return x; 					// 자기 자신이 부모노드이면 반환 후 종료
        return getParent(parent[x], parent); 			// 자기 자신이 부모노드가 아니면 부모님 찾아 재귀 실행
    }
    public static void main(String[] args)throws Exception{
        N 				= read();
        M 				= read();
        parent 			= new int[N+1]; // 부모 노드를 담을 배열 

        for(int i=1; i<=N; i++)
            parent[i] 	= i; // 부모 노드를 자기 자신으로 초기화
        
        for(int i=0; i<M; i++){
            a 			= read();
            b 			= read();
            d 			= read();
            pq.add(new Node(a,b,d));
        }
        while(cnt < N){
            Node now = pq.poll();
            int aParent = getParent(now.a, parent);
            int bParent = getParent(now.b, parent);
            if(aParent != bParent) {
            	cnt++;
            	if(bParent>aParent) {
            		int temp = bParent;
            		bParent = aParent;
            		aParent = temp;
            	}
            	changeParent(aParent, bParent, now.dist);
            }
        }
        
        System.out.println(result);
    }
}