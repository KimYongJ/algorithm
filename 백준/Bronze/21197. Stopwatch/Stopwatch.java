//https://www.acmicpc.net/problem/21197
//1초 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int res = 0;
		int prevTime = 0;
		boolean run = false;
		
		while(N-->0)
		{
			int time = Integer.parseInt(br.readLine());
			
			if(run)
				res += time - prevTime;
			else
				prevTime = time;

			run = !run;
		}
		
		if(run)
		{
			System.out.print("still running");
			return;
		}
		
		System.out.print(res);
	}
}