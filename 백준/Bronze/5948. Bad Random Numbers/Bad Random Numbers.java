//https://www.acmicpc.net/problem/5948
//1초 128MB
//입력 : 7339
//답 : 6

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cnt = 0;
		int now = Integer.parseInt(br.readLine());
		boolean visit[] = new boolean[10000];
		
		while(!visit[now])
		{
			cnt++;
			visit[now] = true;
			int a = (now/10) % 10;
			int b = (now/100) % 10;
			int mid = b*10 + a;
			now = mid * mid;
		}
		
		System.out.print(cnt);
	}
}