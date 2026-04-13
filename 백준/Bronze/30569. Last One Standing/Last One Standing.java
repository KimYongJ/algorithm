//https://www.acmicpc.net/problem/30569
//2초 1024MB
//30 10 10 // 체력, 공격력, 재장전시간 (1<=1,000)
//30 15 19
//답 : player two // player one,player two,draw

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		double h1 = Integer.parseInt(st.nextToken());
		int a1 = Integer.parseInt(st.nextToken());
		int r1 = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		double h2 = Integer.parseInt(st.nextToken());
		int a2 = Integer.parseInt(st.nextToken());
		int r2 = Integer.parseInt(st.nextToken());
		
		int mul1 = (int)Math.ceil(h2 / a1) - 1;
		int mul2 = (int)Math.ceil(h1 / a2) - 1;
		
		int time1 = mul1 * r1;
		int time2 = mul2 * r2;
		
		String res = "draw";
		
		if(time1 > time2)
			res = "player two";
		else if(time2 > time1)
			res = "player one";
		
		System.out.print(res);
	}
}