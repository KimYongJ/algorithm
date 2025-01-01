//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14453
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String args[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N	= Integer.parseInt(br.readLine());
		int H[] = new int[N+1];
		int S[] = new int[N+1];
		int P[] = new int[N+1];
		
		for(int i=1; i<=N; i++)
		{
			switch(br.readLine())
			{
				case "P": S[i] = 1; break;
				case "H": P[i] = 1; break;
				case "S": H[i] = 1; break;
			}
			S[i] += S[i-1];
			H[i] += H[i-1];
			P[i] += P[i-1];
		}
		
		int max = 0;
		for(int i=1; i<=N; i++)
		{
			max = Math.max(max, H[i] + S[N] - S[i]);
			max = Math.max(max, H[i] + P[N] - P[i]);
			max = Math.max(max, S[i] + P[N] - P[i]);
			max = Math.max(max, S[i] + H[N] - H[i]);
			max = Math.max(max, P[i] + S[N] - S[i]);
			max = Math.max(max, P[i] + H[N] - H[i]);
		}
		System.out.print(max);
	}
}