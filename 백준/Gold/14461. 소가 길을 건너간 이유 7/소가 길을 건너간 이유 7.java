// https://github.com/kimyongj/algorithm
import java.util.PriorityQueue;
class Node{
	int y, x, cnt, time;
	Node(int y, int x, int cnt, int time){
		this.y=y;     this.x=x; 
		this.cnt=cnt; this.time=time;
	}
}
class Main{
	
	static final int MAX = 1_000_000_000;
	static int N, ctime, nextY, nextX, nextTime, nextCnt, map[][], time[][][];
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static PriorityQueue<Node> pq;
    static int read() throws Exception {// 빠른 입력을 위한 함수
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
	public static void Dijkstra() {
		time[0][0][0] = 0;
		pq.add(new Node(0,0,0,0));
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			for(int xy[] : dxy) {
				nextY = now.y + xy[0];
				nextX = now.x + xy[1];
				
				if(nextY<0 || nextX<0 || nextY>=N || nextX>=N)
					continue;
				
				nextTime = now.time+ctime;
				nextCnt  = now.cnt + 1;

				if(nextCnt == 3) {
					nextTime += map[nextY][nextX];
					nextCnt = 0;
				}
				
				if(time[nextCnt][nextY][nextX] > nextTime) {
					time[nextCnt][nextY][nextX] = nextTime;
					pq.add(new Node(nextY, nextX, nextCnt, nextTime));
				}
			}
			
		}
	}
	public static void main(String[] args)throws Exception{
		N 		= read();
		ctime 	= read();
		map		= new int[N][N];
		time	= new int[4][N][N];
		pq		= new PriorityQueue<Node>((a,b)->a.time-b.time);
		
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++) 
			{
				map[i][j]	  = read();
				time[0][i][j] = 
				time[1][i][j] = 
				time[2][i][j] = MAX;
			}
		Dijkstra();
		N--;
		System.out.println(
							Math.min(
										Math.min(time[0][N][N], time[1][N][N]),time[2][N][N]
									)
						);
	}
}