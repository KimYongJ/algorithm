//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/15993
//1초 / 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		long odd[] = new long[100_001];
		long eve[] = new long[100_001];
		
		odd[1] = 1;
		odd[2] = 1;
		eve[2] = 1;
		odd[3] = 2;
		eve[3] = 2;
		
		for(int i=4; i<=100_000; i++)
		{
			// i를 홀수를 통해 만드는 것은, i-1을 짝수를 통해 만드는것 + i-2를 짝수를 통해 만드는것, 
			// i-3을 짝수를 통해 만드는것을 더한 것과 같다.
			odd[i] = (eve[i-1] + eve[i-2] + eve[i-3]) % 1_000_000_009;
			eve[i] = (odd[i-1] + odd[i-2] + odd[i-3]) % 1_000_000_009;
		}
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-->0)
		{
			int N = Integer.parseInt(br.readLine());//0<=십만
			sb.append(odd[N]).append(' ')
				.append(eve[N]).append('\n');
		}
		System.out.print(sb);
	}
}
