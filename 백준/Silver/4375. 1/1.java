//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/4375
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			String str = br.readLine();
			if(str == null || "".equals(str))
				break;
			
			int N	= Integer.parseInt(str);
			int cnt = 0;
			int num = 1;
			
			while(true)
			{
				++cnt;
				if(num % N == 0)
					break;
				num *= 10;
				num += 1;
				num %= N;
			}
			
			sb.append(cnt).append('\n');
		}
		System.out.print(sb.toString());
	}
}