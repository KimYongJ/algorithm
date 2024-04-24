// https://github.com/kimyongj/algorithm
import java.util.PriorityQueue;
class Node{
	int y, x, z;
	Node(int y, int x, int z){
		this.y=y; this.x=x; this.z=z;
	}
}
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}},
			Y 		= read(),
			X 		= read(),
			K		= read(),
			map[][]	= new int[Y+2][X+2];
		boolean visit[][]		= new boolean[Y+2][X+2];
		PriorityQueue<Node> pq 	= new PriorityQueue<Node>((a,b)->a.z-b.z);
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++) 
				map[y][x] = read();
		
		for(int y=1; y<=Y; y++) 
		{
			pq.add(new Node(y,	1,	map[y][1]));
			pq.add(new Node(y,	X,map[y][X]));
			visit[y][1] = 
			visit[y][X] = true;
			
		}
		for(int x=2; x<X; x++)
		{
			pq.add(new Node(1,	x, 	map[1][x]));
			visit[1][x] = true;
		}
		
		int nextY, nextX, ans = 0, cnt = 0;
		while(true)
		{
			Node now = pq.poll(); 
			ans = Math.max(ans, now.z);
			if(++cnt >= K) 
				break;
			
			for(int xy[] : dxy) 
			{
				nextY = now.y + xy[0];
				nextX = now.x + xy[1];
				if(map[nextY][nextX] !=0 && !visit[nextY][nextX]) 
				{
					visit[nextY][nextX]= true; 
					pq.add(new Node(nextY,nextX, map[nextY][nextX]));
				}
			}
		}
		System.out.println(ans);
	}
}