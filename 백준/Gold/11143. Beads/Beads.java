//https://github.com/KimYongJ
//https://www.acmicpc.net/problem/11143
class Main{
	
	static long[] arr;
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void update(int treeNode, int s, int e, int originIdx, int diff) {
		if(originIdx < s || e < originIdx)
			return;
		
		arr[treeNode] += diff;
		
		if(s != e)
		{
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
		StringBuilder sb = new StringBuilder();
		int T = read();
		while(T-->0)
		{
			int N = read();
			int t = read() + read();
			int H = (int)Math.ceil(Math.log(N) / Math.log(2)) + 1;
			
			arr = new long[(1 << H)];
			
			while(t-->0)
			{
				char c = (char)System.in.read();
				int i = read();
				int j = read();
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