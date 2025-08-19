//https://www.acmicpc.net/problem/2979
//1초 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int tlen = 100;
		int money[] = new int[4];
		int time[] = new int[tlen + 2];
		money[1] = Integer.parseInt(st.nextToken());
		money[2] = Integer.parseInt(st.nextToken());
		money[3] = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<3; i++)
		{
			st = new StringTokenizer(br.readLine());
			time[Integer.parseInt(st.nextToken())+1]++;
			time[Integer.parseInt(st.nextToken())+1]--;
		}
		
		int sum = 0;
		
		for(int i=1; i<=tlen; i++)
		{
			time[i] += time[i-1]; // 현재 누적합
			
			sum += money[time[i]] * time[i]; // 누적합을 구한 후 최종 답을 구함
		}
		
		System.out.print(sum);
	}
}