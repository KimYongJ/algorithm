//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11561
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0)
		{
			long N = Long.parseLong(br.readLine());
			long s = 1;
			long e = (long)Math.sqrt(2 * N);
			long res = 0;
			
			N *= 2;
			
			while(s <= e)
			{
				long mid = (s + e) / 2;
				if(mid * (mid + 1) <= N) // 등차 수열공식 n(n+1)/2 간소화 후 연산진행
				{
					s = mid + 1;
					res = mid;
				}
				else
					e = mid - 1;
			}
			sb.append(res).append('\n');
		}
		System.out.print(sb.toString());
	}
}