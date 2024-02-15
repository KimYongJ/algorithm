// github.com/KimYongJ/algorithm

// 특징 : 어떤 점에 최적보다 더 늦게 도착해도 클립보드에 원소가 더 많으면 그게 나중에 답이될 수도 있어서 2차원 배열로 해야한다.

import java.util.ArrayDeque;
import java.util.Arrays;

class Main{
	static final int MAX = 100_000_000;
	static int goal, MIN, time, LENG, emtOfTime[][];
	
	// 빠른 입력을 위한 함수
    static int read() throws Exception 
    {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
    
	public static void BFS() 
	{
		ArrayDeque<Point> q = new ArrayDeque<Point>() {{add(new Point(1,0,0));}};

		int nEmt, nTime;
		while(!q.isEmpty()) 
		{
			Point now = q.poll(); // 큐에서 하나를 꺼낸다.
			nTime = now.time + 1;
			
			if(now.emt == goal) 
			{   // 종료조건
				MIN = Math.min(MIN, emtOfTime[now.emt][now.clip]);
				continue;
			}
			

			// 클립보드에 복사
			if(emtOfTime[now.emt][now.emt] > nTime) 
			{
				emtOfTime[now.emt][now.emt] = nTime;
				q.add(new Point(now.emt, nTime, now.emt));
			}
			
			// 클립보드있는 것을 화면에 붙여 넣기
			if(now.clip > 0) 
			{
				nEmt = now.emt + now.clip;
				if(0<nEmt && nEmt <LENG && emtOfTime[nEmt][now.clip] > nTime) 
				{
					emtOfTime[nEmt][now.clip] = nTime;
					q.add(new Point(nEmt, nTime, now.clip));
				}
			}
			
			// 이모티콘 삭제 
			nEmt = now.emt-1;
			if(0<nEmt && nEmt <LENG && emtOfTime[nEmt][now.clip] > nTime) 
			{
				emtOfTime[nEmt][now.clip] = nTime;
				q.add(new Point(nEmt, nTime, now.clip));
			}
			
		}
	}
	public static void main(String[] args)throws Exception
	{
		goal 		= read();
		MIN 		= Integer.MAX_VALUE;
		LENG		= (int) (goal*1.5);
		emtOfTime 	= new int[LENG][LENG]; // 이모티콘 갯수당 걸린 시간
		
		for(int i=0; i<LENG; i++)
			Arrays.fill(emtOfTime[i], MAX);
		
		BFS();
		
		System.out.println(MIN);
	}
	static class Point
	{
		int emt, time, clip;
		Point(int emt, int time,int clip)
		{
			this.emt 	= emt;
			this.time 	= time;
			this.clip 	= clip;
		}
	}
}