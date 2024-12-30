//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17425
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int MAX = 1_000_000;
		long a[] = new long[MAX+1];
		long g[] = new long[MAX+1];
		a[1] = 1;
		for(int n=2; n<=MAX; n++)
		{
			++a[n];
			for(int i=1; i*n<=MAX; i++)
				a[n*i] += n;
		}
		
		for(int i=1; i<=MAX; i++)
			g[i] = g[i-1] + a[i];
		
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
			sb.append(g[Integer.parseInt(br.readLine())]).append('\n');

		System.out.print(sb);
	}
}