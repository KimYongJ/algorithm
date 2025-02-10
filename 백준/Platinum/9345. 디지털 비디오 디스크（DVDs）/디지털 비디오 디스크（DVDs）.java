//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/9345
//2초 / 256MB

class Node{
	int min, max;
	Node(int min, int max){
		this.min=min;
		this.max=max;
	}
}
class Main{
	
	static int N, K, LEN;
	static int arr[];
	static Node node[];
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int T = read();
		while(T-->0)
		{
			N	= read();// 노드 수 1<=십만
			K	= read();// 쿼리 수 1<=오만
			arr = new int[N];
			LEN = N << 2;
			node= new Node[LEN];
			
			for(int i=1; i<N; i++)
				arr[i] = i;

			init(1, 0, N-1);
			
			while(K-->0)
			{
				int f = read();
				int l = read();
				int r = read();
				if(f == 0)
				{
					update(1, 0, N-1, l, arr[r]);
					update(1, 0, N-1, r, arr[l]);
					int tmp = arr[r];
					arr[r] = arr[l];
					arr[l] = tmp;
				}
				else
				{
					Node res = query(1, 0, N-1, l, r);
					sb.append(res.min == l && res.max == r ? "YES" : "NO").append('\n');
				}
			}
		}
		System.out.print(sb);
	}
	public static Node query(int treeNode, int s, int e, int left, int right) {
		if(e < left || right < s)
			return new Node(100_001, 0);
		
		if(left <= s && e<= right)
			return node[treeNode];
		int mid = (s + e) >> 1;
		
		Node l = query(treeNode << 1, s, mid, left, right);
		Node r = query(treeNode << 1 | 1, mid + 1, e, left, right);

		return new Node(Math.min(l.min, r.min), Math.max(l.max, r.max));
	}
	public static void update(int treeNode, int s, int e, int idx, int value) {
		if(e < idx || idx < s)
			return;
		if(s == e)
		{
			node[treeNode].min = node[treeNode].max = value;
			return;
		}
		
		int mid = (s + e) >> 1;
		
		update(treeNode << 1, s, mid, idx, value);
		update(treeNode << 1 | 1, mid + 1, e, idx, value);
		
		node[treeNode].min =  Math.min(node[treeNode << 1].min, node[treeNode<<1|1].min);
		node[treeNode].max =  Math.max(node[treeNode << 1].max, node[treeNode<<1|1].max);
	}
	public static void init(int treeNode, int s, int e) {
		if(s == e)
		{
			node[treeNode] = new Node(s,s);
			return;
		}
		int mid = (s + e) >> 1;
		init(treeNode << 1, s, mid);
		init(treeNode << 1 | 1, mid + 1, e);
		node[treeNode] = new Node(
				 Math.min(node[treeNode << 1].min, node[treeNode<<1|1].min),
				Math.max(node[treeNode << 1].max, node[treeNode<<1|1].max)
		);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}