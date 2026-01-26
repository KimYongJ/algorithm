//https://www.acmicpc.net/problem/31845
//1초 1024MB
//8 10 // 카드 수(1<=1000), 턴수(1<=1000)
//3 1 -6 -4 -5 9 -10 -1 // 카드 쌍을 만들 때 얻을 수 있는 점수(-1000<=1000)
//답 : 13

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());// 카드 수(1<=1000)
		int M = Integer.parseInt(st.nextToken());// 턴수(1<=1000)
		int S[] = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			S[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(S);
		
		int T = Math.min((N + 1) / 2, M);
		int score = 0;
		
		int idx = N;
		
		while(T-->0)
		{
			int num = S[--idx];
			
			if(num <= 0)
				break;
			
			score += num;
		}
		
		System.out.print(score);
	}
}