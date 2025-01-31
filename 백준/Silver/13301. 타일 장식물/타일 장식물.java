//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/13301
//2초 / 512mb
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N	= Integer.parseInt(br.readLine());
		if(N <= 2)
		{
			System.out.print(N == 1 ? 4 : 6);
			return;
		}
		long a	= 4;
		long b	= 6;
		long c	= 0;
		
		for(int i=3; i<=N; i++)
		{
			c = a + b;
			a = b;
			b = c;
		}
		
		System.out.print(c);
	}
}