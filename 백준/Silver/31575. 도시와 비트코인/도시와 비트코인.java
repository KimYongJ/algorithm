//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/31575
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
class Node{
	int y, x; Node(int y, int x){
		this.y=y; this.x=x;
	}
}
class Main{
	public static void main(String[] args)throws Exception{
		final int dxy[][] = {{0,1},{1,0}};
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		int map[][] = new int[Y + 2][X + 2];
		
		for(int y=1; y<=Y; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=X; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		ArrayDeque<Node> q = new ArrayDeque<>();
		q.add(new Node(1,1));
		map[1][1] = 0;
		while(!q.isEmpty())
		{
			Node now = q.poll();
			if(now.y==Y && now.x==X) {
				System.out.print("Yes");
				return;
			}
			for(int xy[] : dxy) {
				int nextY = now.y + xy[0];
				int nextX = now.x + xy[1];
				if(map[nextY][nextX] == 1) {
					map[nextY][nextX] = 0;
					q.add(new Node(nextY, nextX));
				}
			}
		}
		System.out.print("No");
	}
}