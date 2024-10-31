//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14570
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Node{
	int left, right;
	Node(int l, int r){left=l; right=r;}
}
class Main{
	
	static Node adNode[];
	static int res;
	static long K;
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
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N	= Integer.parseInt(br.readLine());
		adNode	= new Node[N+1];
		
		for(int i=1; i<=N; i++)
		{
			st			= new StringTokenizer(br.readLine());
			int l		= Integer.parseInt(st.nextToken());
			int r		= Integer.parseInt(st.nextToken());
			adNode[i]	= new Node(l, r);
		}
		
		K = Long.parseLong(br.readLine());
		
		add_DFS(1, K);
	}
}