//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/20303
//1초 1024MB
class Node{
	int node;
	Node next;
	Node(int node, Node next){
		this.node=node;
		this.next=next;
	}
}
class Main{
	
	static int N, M, K, cnt, candysum;
	static int[] candy, childCnt, candyCnt;
	static Node[] adNode;
	static boolean[] visit;
	
	public static void main(String[] args)throws Exception{
		N		= read();// 거리에 있는 아이들 수(1<=삼만)
		M		= read();// 아이들의 친구 관계수(0<=십만)
		K		= read()-1;// 뺏을 수 있는 아이수(1<=삼천)
		candy	= new int[N + 1];
		childCnt= new int[N + 1];
		candyCnt= new int[N + 1];
		adNode	= new Node[N + 1];
		visit	= new boolean[N + 1];
		
		for(int i=1; i<=N; i++)
			candy[i] = read();
		
		while(M-->0)
		{
			int a = read();
			int b = read();
			adNode[a] = new Node(b, adNode[a]);
			adNode[b] = new Node(a, adNode[b]);
		}
		
		int len = 0;
		for(int i=1; i<=N; i++)
		{
			if(!visit[i])
			{
				
				cnt = 0;
				candysum = 0;
				visit[i] = true;
				DFS(i);
				childCnt[len] = cnt;
				candyCnt[len] = candysum;
				len++;
			}
		}
		int dp[] = new int[K + 1];
		
		for(int i=0; i<len; i++)
			for(int j=K; j>=childCnt[i]; j--)
				dp[j] = Math.max(dp[j], dp[j-childCnt[i]] + candyCnt[i]);
		
		System.out.print(dp[K]);
	}
	public static void DFS(int node) {
		cnt++;
		candysum += candy[node];
		for(Node next=adNode[node]; next!=null; next=next.next)
		{
			if(!visit[next.node])
			{
				visit[next.node] = true;
				DFS(next.node);
			}
		}
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}