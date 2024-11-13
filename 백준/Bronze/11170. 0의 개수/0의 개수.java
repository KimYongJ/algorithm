//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11170

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int c = 0;
			while(++n<=m)
			{
				int num = n;
				if(num == 0)
					++c;
				while(num != 0)
				{
					if(num % 10 == 0)
						++c;
					num /= 10;
				}
			}
			sb.append(c).append('\n');
		}
		System.out.print(sb);
	}
}