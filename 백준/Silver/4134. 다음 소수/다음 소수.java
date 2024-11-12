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
			if(n < 2)
			{
				sb.append(2).append('\n');
				continue;
			}
			while(n <= MAX)
			{
				boolean flag = true;
				for(long i=2; i*i<=n; i++)
					if(n % i == 0)
					{
						flag = false;
						break;
					}

				if(flag)
				{
					sb.append(n).append('\n');
					break;
				}
				++n;
			}
			
		}
		System.out.print(sb.toString());
	}
}