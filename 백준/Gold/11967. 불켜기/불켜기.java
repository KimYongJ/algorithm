// https://github.com/KimYongJ/algorithm

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;

class Point{
	int y,x;
	Point(int y, int x){
		this.y=y; this.x=x;
	}
}
class Main
{	
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int N, M, MAX; // 최대 번호
	static boolean 	light[][],				// 불 켜짐 유무 담을 배열 
					visit[][];				// 방문 했는지 체크
	static ArrayList<Integer>[] adlist;		// 불 켤 수 있는 공간에 대해 인접리스트로 표현
	static HashSet<Integer> hold;			// 불이 꺼져있어 갈 수 없는 곳을 담아 놓는다.
	static ArrayDeque<Point> q;				// BFS시 사용
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
	public static int BFS() {
		int idx, y, x,cnt = 1;
		visit[0][0] = true;	// 방문 처리
		light[0][0] = true;	// 불을 켠다.
		q.add(new Point(0,0));
		
		while(!q.isEmpty()){
			Point now = q.poll();
			
			idx = now.y*N+now.x;
			for(int nextIdx : adlist[idx]) 		// 인접 리스트를 돌면서 해당 부분 불을 켠다.
			{
				y = nextIdx/N;					// 해당 숫자의 좌표를 변환
				x = nextIdx%N;					// 해당 숫자의 좌표를 변환
				if(!light[y][x])
				{
					light[y][x] = true;			// 해당 좌표의 불을 켠다.
					cnt++;						// 불켠 횟수
					if(hold.remove(nextIdx)) 	// 불이 꺼져있어서 못갔던 곳이 불이 켜졌다면 못갔던 정보를담는(hold)곳에서 해당 장소 제거
					{
						q.add(new Point(y,x));	// 해당 장소를 큐에 넣는다.
						visit[y][x] = true; 
					}
				}
			}
			
			for(int xy[] : dxy) {
				y = now.y + xy[0];
				x = now.x + xy[1];
				if(y>=0 && x>=0 && y<N && x<N && !visit[y][x]) {
					if(light[y][x]) 
					{
						visit[y][x] = true;
						q.add(new Point(y,x));	// 불이 켜져있다면 BFS 진행
					}else 
					{
						hold.add(y*N+x);		// 불이 꺼져있다면 대기에 넣는다.
					}
				}
			}
		}
		return cnt;
	}
	public static void main(String[] args)throws Exception{
		N 		= read();
		M 		= read();
		MAX 	= N*N;					// 나올 수 있는 최대 번호
		visit 	= new boolean[N][N];	// BFS 방문 체크 
		light 	= new boolean[N][N];	// 불켜짐 유무를 담을 배열
		adlist 	= new ArrayList[MAX];	// 불 켤 수 있는 공간에 대해 인접리스트로 표현
		q 		= new ArrayDeque<>();	// BFS시 사용
		hold 	= new HashSet<>();		// 불이 꺼져있어 갈 수 없던 곳을 담아 놓는다.
		
		for(int i=0; i<MAX; i++) {
			adlist[i] = new ArrayList<>();
		}
		
		int a,b,c,d, e, f;
		for(int i=0; i<M; i++) {
			a = read()-1;
			b = read()-1;
			c = read()-1;
			d = read()-1;
			e = a*N+b;
			f = c*N+d;
			adlist[e].add(f);		// e에서 f를 갈 수 있다.(불을 켤 수 있다)
		}
		System.out.println( BFS() );
	}
}

