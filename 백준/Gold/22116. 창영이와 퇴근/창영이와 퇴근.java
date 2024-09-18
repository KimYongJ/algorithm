//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/22116
import java.util.Arrays;
import java.util.PriorityQueue;
class Node{
	int y, x, diff;
	Node(int y, int x,int diff){this.y=y; this.x=x;this.diff=diff;}
}
class Main{	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
        // 입력 부분
		int N		= read();		// 1<=천
		int map[][] = new int[N][N];// 1<=십억
		for(int y=0; y<N; y++)
			for(int x=0; x<N; x++)
				map[y][x] = read();

        // 다익스트라 부분
		PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->a.diff - b.diff);
		int visit[][]	= new int[N][N];
		int dxy[][]		= {{0,1},{1,0},{-1,0},{0,-1}};
		
		for(int v[] : visit)
			Arrays.fill(v, 1000000000);
		
		pq.add(new Node(0,0,0));
		visit[0][0] = 0;
		
		while(!pq.isEmpty())
		{
			Node now = pq.poll(); // now에는 현재 이동하면서 만난 경사 차이의 최대값이 있음, 큐에서는 diff가 제일 작은것들만 먼저 꺼냄
			if(now.y == now.x && now.y == N-1)
			{
				System.out.print(now.diff);
				return;
			}
			
			for(int xy[] : dxy)
			{
				int nextY = now.y + xy[0];
				int nextX = now.x + xy[1];
				if(nextY <0 || nextX <0 || nextY >=N || nextX>=N) continue;
				// diff를 최댓 값으로 하고, 
				int diff = Math.max(Math.abs(map[now.y][now.x]- map[nextY][nextX]), now.diff);
				if(visit[nextY][nextX] > diff)// visit은 최소값으로 담기게 하여 최대 값의 최소가 담기도록 만듦
				{
					visit[nextY][nextX] = diff;
					pq.add(new Node(nextY, nextX, diff));
				}
			}
		}
		
		System.out.print(0);
	}
}