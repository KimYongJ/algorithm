//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11962
//2초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static final long MAX = 1L<<62;
	static long[] arr, lazy, sum, min;
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N	= Integer.parseInt(st.nextToken());
		int Q	= Integer.parseInt(st.nextToken());
		int len = N * 4;
		
		arr		= new long[N + 1];
		lazy	= new long[len];
		sum		= new long[len];
		min		= new long[len];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<len; i++)
			min[i] = MAX;
		
		init(1, 1, N);
		
		StringBuilder sb = new StringBuilder();
		while(Q-->0)
		{
			st = new StringTokenizer(br.readLine());
			char flag = st.nextToken().charAt(0);
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			if(flag == 'P')
			{
				int V = Integer.parseInt(st.nextToken());
				update(1, 1, N, A, B, V);
				
				continue;
			}
			else if(flag == 'S')
				sb.append(sumQuery(1, 1, N, A, B));
			else
				sb.append(minQuery(1, 1, N, A, B));
			
			sb.append('\n');
		}
		System.out.print(sb);
	}
	public static long minQuery(int treeNode, int s, int e, int left, int right) {
		
		propagate(treeNode, s, e);
		
		if(right < s || e < left)
			return MAX;
		
		if(left <=s && e <= right)
			return min[treeNode];
		
		int mid = (s + e) >> 1;
		int next= treeNode << 1;

		return Math.min(minQuery(next, s, mid, left, right) ,
						minQuery(next | 1, mid + 1, e, left, right));
	}
	public static long sumQuery(int treeNode, int s, int e, int left, int right) {
		
		propagate(treeNode, s, e);
		
		if(right < s || e < left)
			return 0;
		
		if(left <=s && e <= right)
			return sum[treeNode];
		
		int mid = (s + e) >> 1;
		int next= treeNode << 1;

		return sumQuery(next, s, mid, left, right) + 
				sumQuery(next | 1, mid + 1, e, left, right);
	}
	public static void update(int treeNode, int s, int e, int left, int right, long value) {
		
		propagate(treeNode, s, e);
		
		if(right < s || e < left)
			return;
		
		if(left <=s && e <= right)
		{
			lazy[treeNode] = value;
			propagate(treeNode, s, e);
			return;
		}
		
		int mid = (s + e) >> 1;
		int next= treeNode << 1;
		
		update(next, s, mid, left, right, value);
		update(next | 1, mid + 1, e, left, right, value);
		
		sum[treeNode] = sum[next] + sum[next | 1];
		min[treeNode] = Math.min( min[next], min[next | 1] );
	}
	public static void init(int treeNode, int s, int e) {
		if(s == e)
		{
			sum[treeNode] = min[treeNode] = arr[s];
			return;
		}
		
		int mid = (s + e) >> 1;
		int next= treeNode << 1;
		
		init(next, s, mid);
		init(next | 1, mid + 1, e);
		
		sum[treeNode] = sum[next] + sum[next | 1];
		min[treeNode] = Math.min(min[next], min[next | 1]);
	}
	public static void propagate(int treeNode, int s, int e) {
		if(lazy[treeNode] != 0)
		{
			
			sum[treeNode] += (e - s + 1) * lazy[treeNode];
			min[treeNode] += lazy[treeNode];
			
			if(s != e)
			{
				lazy[treeNode << 1]		+= lazy[treeNode];
				lazy[treeNode << 1 | 1] += lazy[treeNode];
			}
			lazy[treeNode] = 0;
		}
	}
}