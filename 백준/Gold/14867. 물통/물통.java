//https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{new Main().solution();}
	
	int a,b,c,d;
	boolean visit[][];
	ArrayDeque<Point> q;
	public void solution()throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		visit = new boolean[a+1][b+1];
		q = new ArrayDeque<>();
		
		q.add(new Point(0,0,0));
		visit[0][0] = true;
		
		int nextA, nextB, nextCnt;
		while(!q.isEmpty()) {
			Point now = q.poll();
			
			if(now.a == c && now.b == d) {
				System.out.println(now.cnt);
				System.exit(0);
			}
			nextCnt = now.cnt + 1;
			// 왼쪽에 가득 채운다.
			nextA = a;
			nextB = now.b;
			if(!visit[nextA][nextB]) {
				visit[nextA][nextB] = true;
				q.add(new Point(nextA, nextB, nextCnt));
			}
			// 오른쪽에 가득 채운다. 
			nextA = now.a;
			nextB = b;
			if(!visit[nextA][nextB]) {
				visit[nextA][nextB] = true;
				q.add(new Point(nextA, nextB, nextCnt));
			}
			// 왼쪽을 비운다.
			if(!visit[0][now.b]) {
				visit[0][now.b] = true;
				q.add(new Point(0, now.b, nextCnt));
			}
			// 오른쪽을 비운다.
			if(!visit[now.a][0]) {
				visit[now.a][0] = true;
				q.add(new Point(now.a, 0, nextCnt));
			}
			
			// 왼쪽물을 오른쪽으로 비운다. 
			if(now.b+now.a <= b) // 물을 전부 비울 수 있는 경우
			{
				nextB = now.b + now.a;
				if(!visit[0][nextB]) {
					visit[0][nextB] = true;
					q.add(new Point(0, nextB, nextCnt));
				}
			}else {
				nextA = now.a + now.b - b;
				if(!visit[nextA][b]) {
					visit[nextA][b] = true;
					q.add(new Point(nextA, b, nextCnt));
				}
			}
			
			// 오른쪽물을 왼쪽으로 비운다. 
			if(a >= now.a+now.b) { // 물을 전부 비울 수 있는 경우
				nextA = now.a+now.b;
				if(!visit[nextA][0]) {
					visit[nextA][0] = true;
					q.add(new Point(nextA, 0, nextCnt));
				}
			}else {
				nextB = now.a + now.b - a;
				if(!visit[a][nextB]) {
					visit[a][nextB] = true;
					q.add(new Point(a,nextB, nextCnt));
				}
			}
			
		}
		System.out.println(-1);
	}
	
}
class Point{
	int a,b,cnt;
	Point(int a,int b, int cnt){
		this.a = a;
		this.b = b;
		this.cnt = cnt;
	}
}