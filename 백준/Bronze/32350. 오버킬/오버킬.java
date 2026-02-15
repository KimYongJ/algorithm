//https://www.acmicpc.net/problem/32350
//0.5초 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		int cnt = 0;
		int over = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
		{
			int e = Integer.parseInt(st.nextToken());
			
			if(over > 0)
			{
				int damage = over * P / 100;
				e -= damage;
				over = 0;
			}
			if(e <= 0) {
		//		over = -e;
				continue;
			}
				
			while(e > 0)
			{
				++cnt;
				e -= D;
			}
			if(e < 0)
				over = -e;
		}
		
		System.out.print(cnt);
	}
}