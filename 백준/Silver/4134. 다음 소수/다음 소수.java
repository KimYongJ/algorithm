//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/4134
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		final long MAX = 1_000_000_000_000L;
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			long n = Long.parseLong(br.readLine());
			while(!isPrime(n))
				++n;
			sb.append(n).append('\n');
		}
		System.out.print(sb.toString());
	}
	public static boolean isPrime(long n) {
		if(n < 2 || n == 4) return false;
		if(n < 4 || n == 5) return true;
		if(n % 2 == 0 || n % 3 == 0) return false;
		for(long i=5; i*i<=n; i+= 6)
			if(n % i == 0 || n % (i+2) == 0)
				return false;
		return true;
	}
}