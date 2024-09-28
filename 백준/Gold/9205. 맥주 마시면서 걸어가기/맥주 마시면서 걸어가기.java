//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/9205
import java.util.ArrayDeque;
class Node{
	int y, x; Node(int y, int x){this.y=y; this.x=x;}
}
class Main{
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) {
        n = (n << 3) + (n << 1) + (c & 15);}
        return m ? ~n + 1 : n;
    }
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int T = read();						// 1<=50
		while(T-->0)
		{
			int N			= read();		// 편의점개수 (0<=100)
			Node cs[]		= new Node[N];	// 편의점 위치를 담을 배열
			boolean flag	= false;
			boolean visit[] = new boolean[N];
			Node start		= new Node(read(),read());
			
			for(int i=0; i<N; i++)
				cs[i] = new Node(read(),read());
			
			Node last = new Node(read(),read());
			
			ArrayDeque<Node> q = new ArrayDeque<>();
			q.add(start);
			while(!q.isEmpty())
			{
				Node now = q.poll();
				
				if(Math.abs(now.x-last.x) + Math.abs(now.y-last.y) <= 1000)
				{
					flag = true;
					break;
				}
				for(int i=0; i<N; i++)
					if(!visit[i] && Math.abs(now.x-cs[i].x) + Math.abs(now.y-cs[i].y) <= 1000)
					{
						visit[i] = true;
						q.add(cs[i]);
					}
			}
			
			if(flag)
				sb.append("happy");
			else
				sb.append("sad");
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}