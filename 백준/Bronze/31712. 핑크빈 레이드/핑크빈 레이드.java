//https://www.acmicpc.net/problem/31712
//1초 1024MB
//12 19// 주기와 데미지가 주어짐(1<=1,000)
//8 18
//6 15
//102 // 몹의 체력(1<=5,000)
//답 : 12

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int p[] = new int[3];
		int d[] = new int[3];
		
		for(int i=0; i<3; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			p[i] = Integer.parseInt(st.nextToken());// 주기(1<=1,000)
			d[i] = Integer.parseInt(st.nextToken());// 데미지(1<=1,000)
		}
		
		int hp = Integer.parseInt(br.readLine());
		int time = -1;
		
		while(hp > 0)
		{
			++time;
			
			for(int i=0; i<3; i++)
				if(time % p[i] == 0)
					hp -= d[i];
		}
		
		System.out.print(time);
	}
}