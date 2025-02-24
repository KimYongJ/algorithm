//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17407
//0.5초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	
	static int N, Q, total, result;
	static long[] tree, lazy, psum, origin;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		N		= str.length();
		Q		= Integer.parseInt(br.readLine());
		tree	= new long[N * 4];
		lazy	= new long[N * 4];
		psum	= new long[N+1];
		origin	= new long[N+1];
		
		for(int i=1; i<=N; i++)
		{
			origin[i]	= str.charAt(i-1) == '(' ? 1 : -1;
			psum[i]		+= origin[i] + psum[i-1];
			total		+= origin[i];
		}
		
		init(1, 1, N);
		
		while(Q-->0)
		{
			int idx		= Integer.parseInt(br.readLine());
			int value	= origin[idx] == 1 ? -2 : 2;
			total		+= value;	// 토탈 값 갱신
			origin[idx] = origin[idx] == 1 ? -1 : 1;
			
			update(1, 1, N, idx, N, value);
			
			if(tree[1] >= 0 && total == 0)
				result++;
		}
		System.out.print(result);
	}
	public static void update(int treeNode, int s, int e, int left, int right, int value) {
		propagate(treeNode, s, e);
		if(e < left || right < s)
			return;
		if(left<=s && e<= right)
		{
			lazy[treeNode] = value;
			propagate(treeNode, s, e);
			return;
		}
		
		int mid = (s + e) >> 1;
		
		update(treeNode << 1, s, mid, left, right, value);
		update(treeNode << 1 | 1, mid + 1, e, left, right, value);
		
		tree[treeNode] = Math.min(tree[treeNode << 1], tree[treeNode <<1 | 1]);
	}
	public static void propagate(int treeNode, int s, int e) {
		if(lazy[treeNode] != 0)
		{
			tree[treeNode] += lazy[treeNode];
			if(s != e)
			{
				lazy[treeNode << 1]		+= lazy[treeNode];
				lazy[treeNode << 1 | 1] += lazy[treeNode];
			}
			lazy[treeNode] = 0;
		}
	}
	public static void init(int treeNode, int s, int e) {
		if(s == e)
		{
			tree[treeNode] = psum[s];
			return;
		}
		int mid = (s + e) >> 1;
		
		init(treeNode << 1, s, mid);
		init(treeNode << 1 | 1, mid + 1, e);
		
		tree[treeNode] = Math.min(tree[treeNode << 1], tree[treeNode << 1 | 1]);
	}
}
