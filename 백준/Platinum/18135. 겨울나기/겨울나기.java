//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/18135
//2초 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static int N, M;
	static int[] area;
	static long[] tree, lazy;
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 칸수
		M = Integer.parseInt(st.nextToken());	// 영역 수
		area = new int[N + 1];
		tree = new long[M * 4];
		lazy = new long[M * 4];
		
		for(int i=1; i<=M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			for(int s = x; s<=y; s++)	// 영역을 마킹 한다.
				area[s] = i;
			
			if(x <= y)
				update(1, 1, M, area[x], area[y], z);
			else
			{
				update(1, 1, M, area[x], M, z);
				update(1, 1, M, 1, area[y], z);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		while(true)
		{
			st = new StringTokenizer(br.readLine());
			int flag = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			if(flag == 0)
				break;
			
			if(flag == 1)
			{
				long res = 0;
				if(x <= y)
					res = query(1, 1, M, area[x], area[y]);
				else {
					res = query(1, 1, M, area[x], M);
					res += query(1, 1, M, 1, area[y]);
				}
				
				sb.append(res).append('\n');
			}
			else
			{
				int z = Integer.parseInt(st.nextToken());
				if(x <= y)
					update(1, 1, M, area[x], area[y], z);
				else
				{
					update(1, 1, M, area[x], N, z);
					update(1, 1, M, 1, area[y], z);
				}
			}
			
		}
		System.out.print(sb);
	}
	public static void update(int treeNode, int s, int e, int left, int right, long value) {
		
		propagate(treeNode, s, e);
		
		if(right < s || e < left)
			return;
		
		if(left <= s && e <= right)
		{
			lazy[treeNode] += value;
			propagate(treeNode, s, e);
			return;
		}
		
		int mid = (s + e) >> 1;
		int next= treeNode << 1;
		
		update(next, s, mid, left, right, value);
		update(next | 1, mid + 1, e, left, right, value);
		
		tree[treeNode] = tree[next] + tree[next | 1];
	}
	public static long query(int treeNode, int s, int e, int left, int right) {
		
		propagate(treeNode, s, e);
		
		if(right < s || e < left)
			return 0;
		if(left <= s && e <= right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		int next= treeNode << 1;
		
		return query(next, s, mid, left, right) + 
				query(next | 1, mid + 1, e, left, right);
	}
	public static void propagate(int treeNode, int s, int e) {
		if(lazy[treeNode] != 0)
		{
			tree[treeNode] += (e - s + 1) * lazy[treeNode];
			if(s != e)
			{
				lazy[treeNode << 1]		+= lazy[treeNode];
				lazy[treeNode << 1 | 1] += lazy[treeNode];
			}
			lazy[treeNode] = 0;
		}
	}
}