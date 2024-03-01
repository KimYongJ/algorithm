//https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{new Main().solution();}
	
	int a,b,c,d;
	HashSet<String> visit;
	ArrayDeque<Point> q;
	public void solution()throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		visit = new HashSet<>();
		q = new ArrayDeque<>();
		
		q.add(new Point(0,0,0));
		visit.add(0+" "+0);
		
		int nextA, nextB, nextCnt;
		String key;
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
			key = nextA+" "+nextB;
			if(!visit.contains(key)) {
				visit.add(key);
				q.add(new Point(nextA, nextB, nextCnt));
			}
			// 오른쪽에 가득 채운다. 
			nextA = now.a;
			nextB = b;
			key = nextA+" "+nextB;
			if(!visit.contains(key)) {
				visit.add(key);
				q.add(new Point(nextA, nextB, nextCnt));
			}
			// 왼쪽을 비운다.
			key = "0 "+now.b;
			if(!visit.contains(key)) {
				visit.add(key);
				q.add(new Point(0, now.b, nextCnt));
			}
			// 오른쪽을 비운다.
			key = now.a+" 0";
			if(!visit.contains(key)) {
				visit.add(key);
				q.add(new Point(now.a, 0, nextCnt));
			}
			
			// 왼쪽물을 오른쪽으로 비운다. 
			if(now.b+now.a <= b) // 물을 전부 비울 수 있는 경우
			{
				nextB = now.b + now.a;
				key = "0 "+nextB;
				if(!visit.contains(key)) {
					visit.add(key);
					q.add(new Point(0, nextB, nextCnt));
				}
			}else {
				nextA = now.a + now.b - b;
				key = nextA+" "+b;
				if(!visit.contains(key)) {
					visit.add(key);
					q.add(new Point(nextA, b, nextCnt));
				}
			}
			
			// 오른쪽물을 왼쪽으로 비운다. 
			if(a >= now.a+now.b) { // 물을 전부 비울 수 있는 경우
				nextA = now.a+now.b;
				key = nextA+" 0";
				if(!visit.contains(key)) {
					visit.add(key);
					q.add(new Point(nextA, 0, nextCnt));
				}
			}else {
				nextB = now.a + now.b - a;
				key = a+" "+nextB;
				if(!visit.contains(key)) {
					visit.add(key);
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