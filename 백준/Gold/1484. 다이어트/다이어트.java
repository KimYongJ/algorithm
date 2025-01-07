//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1484
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int G	= Integer.parseInt(br.readLine());
		long s	= 1;
		long e	= 2;
		
		while(e <= 50_001)
		{
			long diff = e*e-s*s;
			
			if(diff == G)
			{
				sb.append(e).append('\n');
				++e;
				++s;
			}
			else if(diff<=G)
				++e;
			else
				++s;
		}
		if(sb.length() == 0)
			sb.append(-1);
		System.out.print(sb);
	}
}