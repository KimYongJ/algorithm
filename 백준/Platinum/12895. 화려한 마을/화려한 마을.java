//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/12895
//2초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static int N, T, Q;
	static long[] tree, lazy;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N		= Integer.parseInt(st.nextToken());//집개수 1<=십만
		T		= Integer.parseInt(st.nextToken());//사용할 색개수 1<=30
		Q		= Integer.parseInt(st.nextToken());//작업의 개수 1<=십만
		tree	= new long[N*4];
		lazy	= new long[N*4];
		
		init(1, 1, N);
		
		while(Q-->0)
		{
			st = new StringTokenizer(br.readLine());
			char c = st.nextToken().charAt(0);
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			if(y < x)
			{
				int t = y;
				y = x;
				x = t;
			}
			if(c == 'C')// x~y 사이 z번으로 칠함
			{
				int z = Integer.parseInt(st.nextToken());
				update(1, 1, N, x, y, 1L << (z - 1));
			}
			else// x~y 출력
			{
				long result = query(1, 1, N, x, y);
				sb.append(Long.bitCount(result))
					.append('\n');
			}
		}
		System.out.print(sb);
	}
	public static void init(int treeNode, int s, int e) {
		if(s == e) {
			tree[treeNode] = 1;
			return;
		}
		int mid = (s + e) >> 1;
		init(treeNode << 1, s, mid);
		init(treeNode << 1 | 1, mid + 1, e);
		tree[treeNode] = tree[treeNode << 1] | tree[treeNode << 1 | 1];
	}
	public static long query(int treeNode, int s, int e, int left, int right) {
        propagate(treeNode, s, e);
		if(e < left || right < s)
			return 0;
		if(left <= s && e <= right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		return query(treeNode << 1, s, mid, left, right)
				| query(treeNode << 1 | 1, mid + 1, e, left , right);
	}
	public static void update(int treeNode, int s, int e, int left, int right, long value) {
		propagate(treeNode, s, e);
		
		if(e < left || right < s)
			return;
		if(left<=s && e<=right)
		{
			lazy[treeNode] = value;
			propagate(treeNode, s, e);
			return;
		}
		
		int mid = (s + e) >> 1;
		
		update(treeNode << 1, s, mid, left, right, value);
		update(treeNode << 1 | 1, mid + 1, e, left, right, value);
		
		tree[treeNode] = tree[treeNode << 1] | tree[treeNode << 1 | 1];
	}
	public static void propagate(int treeNode, int s, int e) {
		if(lazy[treeNode] != 0)
		{
			tree[treeNode] = lazy[treeNode];
			if(s!=e)
			{
				lazy[treeNode << 1]		= lazy[treeNode];
				lazy[treeNode << 1 | 1] = lazy[treeNode];
			}
			lazy[treeNode] = 0;
		}
	}
}