//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/22116

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
class Node{
	int y, x;
	Node(int y, int x){this.y=y; this.x=x;}
}
class Main{
	static int dxy[][] = {{0,1},{1,0},{-1,0},{0,-1}};
	public static boolean check(int map[][], int N, int maxDiff) {
		boolean visit[][] = new boolean[N][N];
		ArrayDeque<Node> q = new ArrayDeque<>();
		visit[0][0] = true;
		q.add(new Node(0,0));
		while(!q.isEmpty())
		{
			Node now = q.poll();
			if(now.y == now.x && now.y == N-1)
			{
				return true;
			}
			for(int xy[] : dxy)
			{
				int nextY = now.y + xy[0];
				int nextX = now.x + xy[1];
				if(
						nextY>=0 && nextX>=0 && nextY < N && nextX < N 
						&& !visit[nextY][nextX]
						&& Math.abs(map[now.y][now.x]- map[nextY][nextX]) <= maxDiff
					)
				{
					visit[nextY][nextX] = true;
					q.add(new Node(nextY, nextX));
				}
			}
		}
		return false;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	// 1<=천
		int map[][] = new int[N][N];				// 1<=십억
		for(int y=0; y<N; y++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int x=0; x<N; x++)
				map[y][x] = Integer.parseInt(st.nextToken());
		}
		
		int s = 0;
		int e = 1_000_000_001;
		int res = 0;
		while(s <= e)
		{
			int mid = (s + e) >> 1; // 최대 차이날 수 있는 경사
			if(check(map, N, mid)) {
				e = mid - 1;
				res = mid;
			}else s = mid + 1;
		}
		System.out.print(res);
	}
}