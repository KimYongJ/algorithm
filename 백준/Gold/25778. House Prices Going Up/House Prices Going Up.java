//https://github.com/KimYongJ
//https://www.acmicpc.net/problem/25778

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static long[] arr, seg;
	public static long init(int treeNode, int s, int e) {
		if(s == e)
			return seg[treeNode] = arr[s];
		
		int mid = (s + e) >> 1;
		int nextNode = treeNode << 1;
		
		return seg[treeNode] = init(nextNode, s, mid) + init(nextNode | 1, mid + 1, e);
	}
	public static void update(int treeNode, int s, int e, int originIdx, int diff) {
		if(originIdx < s || e < originIdx)
			return;
		
		seg[treeNode] += diff;
		
		if(s != e) {
			int mid		= (s + e) >> 1;
			int nextNode= treeNode << 1;
			update(nextNode, s, mid, originIdx, diff);
			update(nextNode | 1, mid + 1, e, originIdx, diff);
		}
	}
	public static long query(int treeNode, int s, int e, int left, int right) {
		if(right < s || e < left)
			return 0;
		if(left <= s && e <= right)
			return seg[treeNode];
		
		int mid = (s + e) >> 1;
		int nextNode = treeNode << 1;
		return query(nextNode, s, mid, left, right) + query(nextNode | 1, mid + 1, e, left, right);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int H = (int)Math.ceil(Math.log(N) / Math.log(2)) + 1;
		
		arr = new long[N+1];
		seg = new long[(1<<H)];
		
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(br.readLine());
		
		init(1, 1, N);
		
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			char c = st.nextToken().charAt(0);
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(c == 'U')
				update(1, 1, N, a, b);
			else
				sb.append( query(1, 1, N, a, b) )
					.append('\n');
		}
		System.out.print(sb.toString());
	}
}