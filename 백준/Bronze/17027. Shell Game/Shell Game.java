//https://www.acmicpc.net/problem/17027
//2초 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int score[] = new int[4];
		int now[] = {0,1,2,3};
		
		while(N-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			for(int i=1; i<=3; i++)
			{
				if(now[i] == a)
					now[i] = b;
				else if(now[i] == b)
					now[i] = a;
				if(now[i] == g)
					score[i]++;
			}
		}
		
		System.out.print(Math.max(score[1], Math.max(score[2], score[3])));
	}
}