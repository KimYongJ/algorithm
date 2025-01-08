//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/3673
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());// 테스트 케이스 수 (1<=200)
		while(T-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M		= Integer.parseInt(st.nextToken()); // 나눌 수(1<=백만)
			int N		= Integer.parseInt(st.nextToken()); // 수열의크기(1<=오만)
			long arr[]	= new long[N+1];
			st = new StringTokenizer(br.readLine());
			long mod[]	= new long[M];
			
			for(int i=1; i<=N; i++)
			{
				arr[i] = Integer.parseInt(st.nextToken()) + arr[i-1];
				mod[(int)(arr[i] % M)]++;
			}
			
			long res = mod[0];
			
			for(int i=0; i<M; i++)
				res += mod[i] * (mod[i]-1) / 2;
			
			sb.append(res).append('\n');
			
		}
		System.out.print(sb);
	}
}