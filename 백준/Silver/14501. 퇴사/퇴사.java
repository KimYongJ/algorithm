//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14501

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int max;
	static int N, T[], P[];
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		T = new int[N + 1];
		P = new int[N + 1];
		
		for(int i=1; i<=N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());	// 걸리는 기간
			P[i] = Integer.parseInt(st.nextToken());	// 보상
		}
		
		bruteforce(1, 0);// 현재 일자, 금액의 합
		
		System.out.print(max);
	}
	// day까지 일했을 때 sum의 값 + 일안하고 day로 넘어왔을 때 sum의 값
	public static void bruteforce(int day, int sum) {
		if(N < day)
		{
			max = Math.max(max, sum);
			return;
		}
		
		bruteforce(day + 1, sum);
		
		if(day + T[day]-1 <= N)		// 일을 마친 후의 날짜가 N이하일 때만
			bruteforce(day + T[day], sum + P[day]);
	}
}