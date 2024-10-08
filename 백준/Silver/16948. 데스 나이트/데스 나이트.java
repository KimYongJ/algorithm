//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/16948
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

class Node{int y, x, cnt;Node(int y, int x,int c){this.y=y; this.x=x;this.cnt=c;}}

class Main{
	public static void main(String[] args)throws Exception{
		final int dxy[][] = {{-2, -1}, {-2, 1}, {0,-2}, {0, 2}, {2, -1}, {2, 1}};
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	// 맵크기(5<=200)
		StringTokenizer st = new StringTokenizer(br.readLine());
		int sy = Integer.parseInt(st.nextToken());
		int sx = Integer.parseInt(st.nextToken());
		int ey = Integer.parseInt(st.nextToken());
		int ex = Integer.parseInt(st.nextToken());
		boolean visit[][] = new boolean[N][N];
		
		ArrayDeque<Node> q = new ArrayDeque<>();
		visit[sy][sx] = true;
		q.add(new Node(sy, sx, 0));
		while(!q.isEmpty()) {
			Node now = q.poll();
			
			if(now.y==ey && now.x==ex)
			{
				System.out.print(now.cnt);
				return;
			}
			int nextCnt = now.cnt + 1;
			for(int xy[] : dxy)
			{
				int nextY = now.y + xy[0];
				int nextX = now.x + xy[1];
				if(nextY<0 || nextX<0 || N<=nextY || N<=nextX || visit[nextY][nextX])
					continue;
				visit[nextY][nextX] = true;
				q.add(new Node(nextY,nextX, nextCnt));
			}
		}
		System.out.print(-1);
	}
}