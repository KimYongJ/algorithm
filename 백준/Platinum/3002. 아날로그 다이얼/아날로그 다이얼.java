//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/3002
//1초 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static int N, M;
	static int[] arr;
	static int[] tree[], lazy;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N		= Integer.parseInt(st.nextToken());	// 1<=이십오만
		M		= Integer.parseInt(st.nextToken());	// 1<=이십오만
		arr		= new int[N + 1];
		tree	= new int[N * 4][10];
		lazy	= new int[N * 4];
		
		String str = br.readLine();
		for(int i=1; i<=N; i++)
			arr[i] = str.charAt(i-1) - '0';
		
		init(1, 1, N);
		
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			sb.append( query(1, 1, N, s, e) )
				.append('\n');
			
			update(1, 1, N, s, e);
		}
		System.out.print(sb);
	}
	public static void init(int treeNode, int s, int e) {
		if(s == e)
		{
			tree[treeNode][arr[s]] = 1;
			return;
		}
		
		int mid = (s + e) >> 1;
		
		init(treeNode << 1, s, mid);
		init(treeNode << 1 | 1, mid + 1, e);
		
		for(int i=0; i<10; i++)
			tree[treeNode][i] = tree[treeNode << 1][i] + tree[treeNode << 1 | 1][i];
	}
	public static void update(int treeNode, int s, int e, int left, int right) {
		if(e < left || right < s)
			return;
		if(left<=s && e<=right)
		{
			lazy[treeNode] = 1;
			propagate(treeNode, s, e);
			return;
		}
		
		int mid = (s + e) >> 1;
		
		update(treeNode << 1, s, mid, left, right);
		update(treeNode << 1 | 1, mid + 1, e, left, right);
		
		for(int i=0; i<10; i++)
			tree[treeNode][i] = tree[treeNode << 1][i] + tree[treeNode << 1 | 1][i];
	}
	public static int query(int treeNode, int s, int e, int left, int right) {
		
		propagate(treeNode, s, e);
		
		if(e < left || right < s)
			return 0;
		if(left<=s && e<=right)
			return getSum(tree[treeNode]);
		
		int mid = (s + e) >> 1;
		
		return query(treeNode << 1, s, mid, left, right)
				+ query(treeNode << 1 | 1, mid + 1, e, left, right);
	}
	public static void propagate(int treeNode, int s, int e) {
		if(lazy[treeNode] != 0)
		{

			int arr[] = new int[10];
			
			for(int i=0; i<10; i++)
			{
				int idx = i + lazy[treeNode];
				idx %= 10;
				arr[idx] = tree[treeNode][i];
			}
			
			tree[treeNode] = arr;
			
			if(s!=e)
			{
				lazy[treeNode << 1]		+= lazy[treeNode];
				lazy[treeNode << 1 | 1]	+= lazy[treeNode];
			}
			
			lazy[treeNode] = 0;
		}
	}
	public static int getSum(int[] arr) {
		int res = 0;
		for(int i=1; i<10; i++)
			res += arr[i] * i;
		return res;
	}
}
