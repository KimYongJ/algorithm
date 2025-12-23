//https://www.acmicpc.net/problem/14530
//2초 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int distSum = 0;
		int plus = 1;
		int mul = 1;
		int prev = x;
		
		while(true)
		{
			int nextX = x + plus * mul;
			
			int min = Math.min(nextX, prev);
			int max = Math.max(nextX, prev);
			
			if(min <= y && y <= max)
			{
				distSum += Math.abs(prev - y);
				break;
			}
			
			distSum += Math.abs(prev - nextX);
			
			prev = nextX;
			mul = -mul;
			plus *= 2;
		}
		

		System.out.print(distSum);
	}
}