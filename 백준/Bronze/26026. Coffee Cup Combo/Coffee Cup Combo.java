//https://www.acmicpc.net/problem/26026
//1초 1024MB
//10 // 강의 개수
//0100010100 // 1이면 커피머신이 있는 강의
//깨어있는 강의 수 : 8

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Integer.parseInt(br.readLine());

		int sum = 0;
		int awake = 0;
		
		for(char c : br.readLine().toCharArray())
		{
			if(c == '1')
				awake = 3;
			
			if(awake > 0)
			{
				--awake;
				++sum;
			}
		}
		
		System.out.print(sum);
	}
}