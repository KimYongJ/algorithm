//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/1717
public class Main {
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static int find(int a, int[] parent) {
		if(parent[a] == a) return a;
		return parent[a] = find(parent[a], parent);
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb	= new StringBuilder();
		final String yes	= "YES";
		final String no		= "NO";
		
		int N			= read();
		int T			= read();
		int parent[]	= new int[N+1];
		
		for(int i=1; i<=N; i++)
			parent[i] = i;
		
		while(T-->0)
		{
			int cmd = read();
			int a	= read();
			int b	= read();
			if(b < a)
			{
				int dummy = b;
				b = a;
				a = dummy;
			}
			
			int aParent = find(a, parent);
			int bParent = find(b, parent);
			
			if(cmd == 1)
				sb.append(aParent == bParent ? yes : no).append('\n');
			else
				parent[bParent] = aParent;
		}
		
		System.out.print(sb.toString());
	}
}