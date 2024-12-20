//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/24542
public class Main {
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static int find(int node, int[] parent) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node], parent);
	}
	public static void main(String[] args)throws Exception{
		int N			= read();		// 교육생 수 N(2<=이십만)
		int M			= read();		// 친분 관계의 수(1<=N-1)
		int parent[]	= new int[N+1];
		int cnt[]		= new int[N+1];
		
		for(int i=1; i<=N; i++)
		{
			parent[i] = i;
			cnt[i] = 1;
		}
		
		while(M-->0)
		{
			int a = read();
			int b = read();
			if(b < a)
			{
				int dummy = b;
				b = a;
				a = dummy;
			}
			
			int aParent = find(a, parent);
			int bParent = find(b, parent);
			
			parent[bParent] = aParent;
			cnt[aParent] += cnt[bParent];
		}

		long res = 1;
		for(int i=1; i<=N; i++)
			if(parent[i] == i)
				res = (res * cnt[i]) % 1_000_000_007;

		System.out.print(res);
	}
}