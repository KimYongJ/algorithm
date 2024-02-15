// github.com/KimYongJ/algorithm

import java.util.ArrayDeque;

class Main{
	static int goal;
	static boolean visit[][];
	
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
		visit[1][0] = true;
		int nEmt, nTime;
		while(!q.isEmpty()) 
		{

			Point now = q.poll(); // 큐에서 하나를 꺼낸다.
			if(now.emt == goal) 
			{   // 종료조건
				System.out.println(now.time);
				return;
			}
			
			nTime = now.time + 1;

			// 클립보드에 복사
			if(!visit[now.emt][now.emt]) 
			{
				visit[now.emt][now.emt] = true;
				q.add(new Point(now.emt, nTime, now.emt));
			}
			
			// 클립보드있는 것을 화면에 붙여 넣기
			if(now.clip > 0) 
			{
				nEmt = now.emt + now.clip;
				if(1<nEmt && nEmt <1001 && !visit[nEmt][now.clip]) 
				{
					visit[nEmt][now.clip] = true;
					q.add(new Point(nEmt, nTime, now.clip));
				}
			}
			
			// 이모티콘 삭제 
			nEmt = now.emt-1;
			if(1<nEmt && nEmt <1001 && !visit[nEmt][now.clip]) 
			{
				visit[nEmt][now.clip] = true;
				q.add(new Point(nEmt, nTime, now.clip));
			}
		}
		
			
		
	}
	public static void main(String[] args)throws Exception
	{
		goal 		= read();
		visit 		= new boolean[1001][1001]; // 이모티콘 갯수당 걸린 시간
		BFS();
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