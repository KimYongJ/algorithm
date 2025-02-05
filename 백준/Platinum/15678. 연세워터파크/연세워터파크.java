//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/15678
//1초 / 128mb
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static final long MIN = Long.MIN_VALUE;
	static int N, D;
	static long[] arr, tree, dp;
	
	public static void main(String[] args)throws Exception{ 
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());	// 2<=십만
		D		= Integer.parseInt(st.nextToken());	// 2<=N-1
		tree	= new long[N<<2];
		arr		= new long[N+1];
		dp		= new long[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		
		long max = MIN;
		for(int i=1; i<=N; i++)
		{
			dp[i] = arr[i];
			
			long maxValue = query(1, 1, N, i-D, i-1);
			
			if(maxValue != MIN)
				dp[i] = Math.max(arr[i], arr[i] + maxValue);
			
			update(1, 1, N, i, dp[i]);
			
			max = Math.max(max, dp[i]);
		}
		System.out.print(max);
	}
	public static void update(int treeNode, int s, int e, int idx, long value) {
		if(e<idx || idx< s)
			return;
		if(s == e)
		{
			tree[treeNode] = value;
			return;
		}
		int nextNode= treeNode << 1;
		int mid		= (s + e) >> 1;
		update(nextNode, s, mid, idx, value);
		update(nextNode | 1, mid + 1, e, idx, value);
		
		tree[treeNode] = Math.max(tree[nextNode], tree[nextNode |1]);
	}
	public static long query(int treeNode, int s, int e, int left, int right) {
		if(e < left || right < s)
			return MIN;
		if(left<=s && e<=right)
			return tree[treeNode];
		
		int nextNode= treeNode << 1;
		int mid		= (s + e) >> 1;
		long max1	= query(nextNode, s, mid, left, right);
		long max2	= query(nextNode | 1, mid + 1, e, left, right);
		
		return Math.max(max1, max2);
	}
}