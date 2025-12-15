//https://www.acmicpc.net/problem/28239
//1초 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			long m = Long.parseLong(br.readLine());
			boolean isContinue = true;
			
			for(int x = 0; x<63 && isContinue; x++)
			{
				long X = 1L << x;
				for(int y = x; y<63; y++)
				{
					long Y = 1L << y;
					if(X + Y == m)
					{
						sb.append(x).append(' ').append(y).append('\n');
						isContinue = false;
						break;
					}
				}
			}
			
		}
		System.out.print(sb);
	}
}

