//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/18436
class Main{
	
	static int[] arr, even;
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static int initEven(int treeNode, int s, int e) {
		if(s == e)
			return even[treeNode] = arr[s]%2 == 0 ? 1 : 0;
		
		int mid			= (s + e) >> 1;
		int nextNode	= treeNode << 1;
		
		return even[treeNode] = initEven(nextNode, s, mid) + initEven(nextNode | 1, mid + 1, e);
	}
	
	public static void update(int treeNode, int s, int e, int originIdx, int diff) {
		if(originIdx < s || e < originIdx)
			return;
		
		even[treeNode] += diff;
		
		if(s != e)
		{
			int mid			= (s + e) >> 1;
			int nextNode	= treeNode << 1;

			update(nextNode, s, mid, originIdx, diff);
			update(nextNode | 1, mid + 1, e, originIdx, diff);			
		}
	}
	
	public static int queryEven(int treeNode, int s, int e, int left, int right) {
		if(right < s || e < left)
			return 0;
		
		if(left <= s && e <= right)
			return even[treeNode];
		
		int mid			= (s + e) >> 1;
		int nextNode	= treeNode << 1;
		
		return queryEven(nextNode, s, mid, left, right) + queryEven(nextNode | 1, mid + 1, e, left, right);
	}
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int N	= read();										// 1<=10만
		int H	= (int)Math.ceil(Math.log(N) / Math.log(2));	// 트리의 높이
		arr		= new int[N+1];
		even	= new int[1<<(H+1)];

		for(int i=1; i<=N; i++)
			arr[i] = read();
		
		initEven(1, 1, N);
		
		int Q = read();
		
		while(Q-->0)
		{
			int cmd = read();
			int a	= read();
			int b	= read();
			
			if(cmd == 1)
			{
				int now = arr[a] % 2;
				int next= b % 2;
				if(now != next)
				{
					arr[a] = b;
					update(1, 1, N, a, arr[a]%2 == 0 ? 1 : -1);
				}
			}
			else
			{
				int cnt = queryEven(1, 1, N, a, b);
				
				if(cmd == 3)
					cnt = (b-a+1) - cnt;
				
				sb.append(cnt).append('\n');
			}
		}
		System.out.print(sb);
	}
}