//https://www.acmicpc.net/problem/14470
//2초 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int a = Integer.parseInt(br.readLine());// 원래 고기 온도 -100<=100 and 0이아님
		int b = Integer.parseInt(br.readLine());// 목표 온도 1<=100
		int c = Integer.parseInt(br.readLine());// 얼어있는 고기 1도를 데우는 데 걸리는 시간1<=100
		int d = Integer.parseInt(br.readLine());// 고기를 해동하는데 걸리는 시간 1<=100
		int e = Integer.parseInt(br.readLine());// 얼어 있지 않은 고기를 1도 데우는데 걸리는 시간1<=100
		
		int time = 0;
		
		if(a<0)
		{
			time += (-a * c) + d;
			a = 0;
		}
		
		time += (b-a) * e;
		
		System.out.print(time);
	}
}