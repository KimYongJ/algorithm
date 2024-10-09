//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2665
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
class Node{
	int y, x, cnt;
	Node(int y, int x, int cnt){
		this.y=y; this.x=x; this.cnt=cnt;
	}
}
class Main{
	public static void main(String[] args)throws Exception{
		final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N			= Integer.parseInt(br.readLine());
		int map[][]		= new int[N][N];
		int visit[][]	= new int[N][N];
		int LIMIT		= N*N;
		
		for(int y=0; y<N; y++)
		{
			String str = br.readLine();
			for(int x=0; x<N; x++)
			{
				visit[y][x] = LIMIT;	// 다익스트라를 위해 들어갈 가장 큰수로 마킹
				if(str.charAt(x) == '1')
					map[y][x] = 1;		// 흰방만 마킹
			}
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> a.cnt - b.cnt);
		pq.add(new Node(0, 0, 0));
		while(!pq.isEmpty())
		{
			Node now = pq.poll();
			
			if(now.y + 1 == N && now.y == now.x)
			{
				System.out.print(now.cnt);
				return;
			}
			
			for(int xy[] : dxy) {
				int nextY = now.y + xy[0];
				int nextX = now.x + xy[1];
				if(nextY<0 || nextX<0 || N<=nextY || N<=nextX)
					continue;
				if(map[nextY][nextX] == 1)
				{
					if(now.cnt < visit[nextY][nextX])
					{
						visit[nextY][nextX] = now.cnt;
						pq.add(new Node(nextY, nextX, now.cnt));
					}
				}
				else {
					if(now.cnt + 1 < visit[nextY][nextX])
					{
						visit[nextY][nextX] = now.cnt + 1;
						pq.add(new Node(nextY, nextX, now.cnt + 1));
					}
				}
			}
			
			
		}
	}
}
