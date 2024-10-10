//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/6087
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node{
	int y, x, cnt, beforDir;
	Node(int y, int x, int cnt, int beforDir){
		this.y=y; this.x=x; this.cnt=cnt;
		this.beforDir=beforDir;
	}
}
class Main{
	public static void main(String[] args)throws Exception{
		final int dxy[][]	= {{1,0},{0,1},{-1,0},{0,-1}};
		BufferedReader br	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st	= new StringTokenizer(br.readLine());
		int X				= Integer.parseInt(st.nextToken());
		int Y				= Integer.parseInt(st.nextToken());
		int s[][]			= new int[2][2];
		int counting[][][]	= new int[4][Y][X];
		boolean visit[][]	= new boolean[Y][X];
		int idx				= 0;
		
		for(int y=0; y<Y; y++)
		{
			String str = br.readLine();
			for(int x=0; x<X; x++) {
				char c = str.charAt(x);
				if(c == 'C') {
					s[idx][0] = y;
					s[idx][1] = x;
					++idx;
				}
				else if(c == '*')
					visit[y][x] = true;
			}
		}
		
		for(int z=0; z<4; z++)
			for(int y=0; y<Y; y++)
				Arrays.fill(counting[z][y], 100_001);
			
		
		PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->a.cnt-b.cnt);
		pq.add(new Node(s[0][0], s[0][1], 0, -1));

		int result = Integer.MAX_VALUE;
		
		while(!pq.isEmpty())
		{
			Node now = pq.poll();
			if(now.y==s[1][0] && now.x==s[1][1])
			{
				result = Math.min(result, now.cnt);
				continue;
			}
			for(int i=0; i<4; i++)
			{
				int nextY = now.y + dxy[i][0];
				int nextX = now.x + dxy[i][1];
				int nextCnt = now.cnt + (now.beforDir == i ? 0 : 1);
				if(0<=nextY && 0<=nextX && nextY<Y && nextX<X && !visit[nextY][nextX])
				{
					if(nextCnt < counting[i][nextY][nextX])
					{
						counting[i][nextY][nextX] = nextCnt;
						pq.add(new Node(nextY, nextX, nextCnt, i));
					}
				}
			}
		}
		System.out.print(result - 1);
	}
}