//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14570
class Node{
	int left, right;
	Node(int l, int r){left=l; right=r;}
}
class Main{
	
	static Node adNode[];
	
	static int read() throws Exception {
        int c, N = System.in.read() - 48;
        while ((c = System.in.read()) > 32)
            N = 10 * N + c - 48;
        return N;
    }
	
	public static void add_DFS(int node, long k) {
		int left = adNode[node].left;
		int right= adNode[node].right;
		if(left < 0 && right < 0)		// 자식 노드가 없다면 출력
			System.out.print(node);
		else if(0 < left && 0< right)	// 둘다 자식이 있다면
		{
			if(k % 2 == 1)	// 홀수면 왼쪽이동
				add_DFS(left, (k>>1) + 1);
			else
				add_DFS(right, k>>1);
		}
		else
			add_DFS(0 < left ? left : right, k);
	}
	
	public static void main(String[] args)throws Exception{
		int N	= read();
		adNode	= new Node[N+1];
		
		for(int i=1; i<=N; i++)
			adNode[i]	= new Node(read(), read());
		
		int c;
        long K = System.in.read() - 48;
        while ((c = System.in.read()) > 32)
            K = 10 * K + c - 48;
        
		add_DFS(1, K);
	}
}