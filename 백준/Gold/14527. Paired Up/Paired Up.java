//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14527
//2초 / 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Cow{
	int cnt, time;
	Cow(int c, int t){
		cnt=c; time=t;
	}
}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		Cow cow[]	= new Cow[N];
		
		for(int i=0; i<N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int c	= Integer.parseInt(st.nextToken());
			int t	= Integer.parseInt(st.nextToken());
			cow[i]	= new Cow(c,t);
		}
		// 시간기준 오름 차순
		Arrays.sort(cow, (a,b)->a.time-b.time);
		int s = 0;
		int e = N-1;
		int m = 0;
		while(s<=e)
		{
			m = Math.max(m, cow[s].time + cow[e].time);
			
			if(--cow[s].cnt == 0)++s;
			if(--cow[e].cnt == 0)--e;
		}
		System.out.print(m);
	}
}