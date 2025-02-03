//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17216
//1초 / 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static int N;
	static int[] arr, dp, tree;
	
	public static void main(String[]args)throws Exception{
		final int MAX = 1000;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N	= Integer.parseInt(br.readLine());	// 1<=천
		arr	= new int[N+1];
		dp	= new int[N+1];
		tree= new int[MAX<<2];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			dp[i] = arr[i] = Integer.parseInt(st.nextToken());	// 1<=천
		
		int max = 0;
		
		for(int i=1; i<=N; i++)
		{
			// arr[i]값 자체를 인덱스로 사용, 그 안에 데이터는 dp[i]값을 저장,
			// arr[i]+1범위 부터 1000 범위안에 가장 큰 dp[x]값을 찾아 dp[i]에 더한다.
			// 즉, i보다 작은 0~j까지 dp[0~j]중 가장 큰 dp[j]값을 가져와 더한다.
			dp[i] += query(1, 1, MAX, arr[i] + 1, MAX);
			// arr[i]값에 dp[i]를 넣는다.
			update(1, 1, MAX, arr[i], dp[i]);
			
			max = Math.max(max, dp[i]);
		}
		System.out.print(max);
	}
	public static void update(int treeNode, int s, int e, int idx, int value) {
		if(e<idx || idx < s)
			return ;
		if(s == e)
		{
			tree[treeNode] = value;
			return;
		}
		
		int nextNode= treeNode << 1;
		int mid		= (s + e) >> 1;
		
		update(nextNode, s, mid, idx, value);
		update(nextNode | 1, mid + 1, e, idx, value);
		tree[treeNode] = Math.max(tree[nextNode], tree[nextNode | 1]);
	}
	public static int query(int treeNode, int s, int e, int left, int right) {
		if(e < left || right < s)
			return 0;
		if(left<=s && e<=right)
			return tree[treeNode];
		
		int nextNode= treeNode << 1;
		int mid		= (s + e) >> 1;
		
		return Math.max(
						query(nextNode, s, mid, left, right), 
						query(nextNode | 1, mid + 1, e, left, right)
				);
	}
}
