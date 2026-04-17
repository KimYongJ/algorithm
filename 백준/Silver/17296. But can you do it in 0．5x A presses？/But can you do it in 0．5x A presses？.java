//https://www.acmicpc.net/problem/17296
//1초 512MB
//5 // 스테이지 개수(1<=1000)
//0.5 1.5 0 1.5 1 // 각 스테이지에 필요한 A버튼의 최소 횟수(0<=1000)
//답 : 4

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int cnt = 0;
		boolean first = true;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		while(N-->0)
		{
			double num = Double.parseDouble(st.nextToken());
			
			if(first && num > 0)
			{
				cnt += Math.ceil(num);
				first = false;
				continue;
			}
			
			cnt += Math.floor(num);
		}
		
		System.out.print(cnt);
	}
}