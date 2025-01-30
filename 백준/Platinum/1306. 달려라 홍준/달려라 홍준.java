//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1306
//2초/256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, M;
	static int[] arr, tree;
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());	// 칸수 1<=백만
		M		= Integer.parseInt(st.nextToken());	// 시야 1<=M
		arr		= new int[N+1];
		tree	= new int[N<<2];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		init(1, 1, N);

		StringBuilder sb = new StringBuilder();
		for(int s=M,p=M-1; s<=N-M+1; s++)
			sb.append(query(1,1,N,s-p,s+p)).append(' ');

		System.out.print(sb);
	}
	public static int query(int treeNode, int s, int e, int left, int right) {
		if(e < left || right < s)
			return 0;
		if(left<=s && e<=right)
			return tree[treeNode];
		int nextNode = treeNode << 1;
		int mid = (s + e) >> 1;
		int num1 = query(nextNode, s, mid, left, right);
		int num2 = query(nextNode | 1, mid + 1, e, left, right);
		return Math.max(num1, num2);
	}
	public static void init(int treeNode, int s, int e) {
		if(s == e)
		{
			tree[treeNode] = arr[s];
			return;
		}
		
		int nextNode = treeNode << 1;
		int mid = (s + e) >> 1;
		init(nextNode, s, mid);
		init(nextNode | 1, mid + 1, e);
		
		tree[treeNode] = Math.max(tree[nextNode], tree[nextNode | 1]);
	}
}