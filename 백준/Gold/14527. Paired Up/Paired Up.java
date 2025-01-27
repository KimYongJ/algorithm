//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14527
//2초 / 512MB

import java.util.Arrays;

class Cow{
	int cnt, time;
	Cow(int c, int t){
		cnt=c; time=t;
	}
}

class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();
		Cow cow[]	= new Cow[N];
		
		for(int i=0; i<N; i++)
			cow[i]	= new Cow(read(),read());
		
		// 시간기준 오름 차순
		Arrays.sort(cow, (a,b)->a.time-b.time);
		
		int s = 0;
		int e = N-1;
		int m = 0;
		while(s<=e)
		{
			m = Math.max(m, cow[s].time + cow[e].time);
			
			int min = Math.min(cow[s].cnt, cow[e].cnt);
			
			cow[s].cnt -= min;
			cow[e].cnt -= min;
			
			if(cow[s].cnt <= 0)++s;
			if(cow[e].cnt <= 0)--e;
		}
		System.out.print(m);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}