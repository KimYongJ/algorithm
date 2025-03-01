//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/5676
//1초 256MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int[] tree, arr;
	static int N, K;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		while(true)
		{
			String str = br.readLine();
			if(str == null || "".equals(str))
				break;
			
			st = new StringTokenizer(str);
			N = Integer.parseInt(st.nextToken());//1<=십만
			K = Integer.parseInt(st.nextToken());//1<=십만
			arr = new int[N + 1];
			tree= new int[N << 2];
			
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N;i++)
			{
				arr[i] = Integer.parseInt(st.nextToken());
				if(arr[i]<0)
					arr[i] = -1;
				else if(0<arr[i])
					arr[i] = 1;
			}
			
			init(1, 1, N);
			
			while(K-->0)
			{
				st = new StringTokenizer(br.readLine());
				char cmd = st.nextToken().charAt(0);
				int i = Integer.parseInt(st.nextToken());
				int j = Integer.parseInt(st.nextToken());
				if(cmd == 'C')
				{
					if(j<0)
						j = -1;
					else if(0<j)
						j = 1;
					update(1, 1, N, i, j);
				}
				else
				{
					long res = query(1, 1, N, i, j);
					if(res == 0)
						sb.append(0);
					else
						sb.append(res < 0 ? '-' : '+');
				}
			}
			
			sb.append('\n');
		}
		System.out.print(sb);
	}
	public static void init(int treeNode, int s, int e) {
		if(s == e)
		{
			tree[treeNode] = arr[s];
			return;
		}
		
		int mid = (s + e) >> 1;
						
		init(treeNode << 1, s, mid);
		init(treeNode << 1 | 1, mid + 1 , e);
		tree[treeNode] = tree[treeNode << 1] * tree[treeNode << 1 | 1] ;
	}
	public static void update(int treeNode, int s, int e, int idx, int value) {
		if(idx < s || e < idx)
			return;
		if(s == e)
		{
			tree[treeNode] = value;
			return;
		}
		
		int mid = (s + e) >> 1;
		
		update(treeNode << 1, s, mid, idx, value);
		update(treeNode << 1 | 1, mid + 1, e, idx, value);
		
		tree[treeNode] = tree[treeNode << 1] * tree[treeNode << 1 | 1] ;
	}
	public static int query(int treeNode, int s, int e, int left, int right) {
		if(e < left || right < s)
			return 1;
		if(left<=s && e<=right)
			return tree[treeNode];
		int mid = (s + e) >> 1;
		
		return query(treeNode << 1, s, mid, left, right)
				* query(treeNode << 1 | 1, mid + 1, e, left, right);
	}
}