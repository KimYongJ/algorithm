//https://www.acmicpc.net/problem/10709

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int Y = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		for(int y=0; y<Y; y++)
		{
			String str = br.readLine();
			int val = -1;
			for(int x=0; x<X; x++)
			{
				char c = str.charAt(x);
				
				if(c != 'c' && val >= 0)
					val++;
				else if(c == 'c')
					val = 0;
				
				sb.append(val).append(' ');
			}
			sb.append('\n');
		}
		
		System.out.print(sb);
	}
}