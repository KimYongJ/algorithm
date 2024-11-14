//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/10211
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static int max;
	static int N, arr[];
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			N	= Integer.parseInt(br.readLine());
			arr = new int[N+1];
			max = ~Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int i=1; i<=N; i++)
				arr[i] = Integer.parseInt(st.nextToken()) + arr[i-1];

			for(int i=1; i<=N; i++)
				comb(0, i, N + 1 - i);

			sb.append(max).append('\n');
		}
		System.out.print(sb);
	}
	public static void comb(int s, int e, int depth) {
		if(depth == 0)
			return;
		max = Math.max(max, arr[e] - arr[s]);
		comb(s + 1, e + 1, depth - 1);
	}
}
