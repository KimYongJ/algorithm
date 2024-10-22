//https://github.com/KimYongJ
//https://www.acmicpc.net/problem/11143

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static long[] arr;
	
	public static void update(int treeNode, int s, int e, int originIdx, int diff) {
		if(originIdx < s || e < originIdx)
			return;
		
		arr[treeNode] += diff;
		
		if(s!=e){
			int nextNode = treeNode << 1;
			int mid = (s + e) >> 1;
			update(nextNode, s, mid, originIdx, diff);
			update(nextNode | 1, mid + 1, e, originIdx, diff);
		}
	}
	public static long query(int treeNode, int s, int e, int left, int right) {
		if(right < s || e < left)
			return 0;
		if(left <= s && e <= right)
			return arr[treeNode];
		
		int nextNode = treeNode << 1;
		int mid = (s + e) >> 1;
		return query(nextNode, s, mid, left, right) + query(nextNode | 1, mid + 1, e, left, right);
	}
	public static void main(String args[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
			int H = (int)Math.ceil(Math.log(N) / Math.log(2)) + 1;
			
			arr = new long[(1 << H)];

			while(t-->0)
			{
				st = new StringTokenizer(br.readLine());
				char c = st.nextToken().charAt(0);
				int i = Integer.parseInt(st.nextToken());
				int j = Integer.parseInt(st.nextToken());
				if(c=='P')
					update(1, 1, N, i, j);
				else
					sb.append( query(1, 1, N, i, j) )
						.append('\n');
			}
		}
		System.out.print(sb.toString());
	}
}