//https://www.acmicpc.net/problem/27161
//1초 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int now = 0;
		boolean dir = false;
		
		for(int i=0; i<N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			boolean d = st.nextToken().charAt(0) == 'H';
			int time = Integer.parseInt(st.nextToken());
			
			if(!dir)
			{
				if(++now == 13)
					now = 1;
			}
			else if(--now <= 0)
				now = 12;
			
			if(d && now != time)
				dir = !dir;
			
			sb.append(now).append(' ').append(!d && now == time ? "YES" : "NO").append('\n');
		}
		
		System.out.print(sb);
	}
}