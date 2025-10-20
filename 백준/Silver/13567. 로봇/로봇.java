//https://www.acmicpc.net/problem/13567
//1초 512MB
//11 14 // 한변의 길이(1<=1000), 명령어 개수(1<=1000), 명령어가 유효하지 않으면 -1 출력
//MOVE 10 // 0<=1000
//TURN 0 // 0은 왼쪽, 1은 오른쪽90도 회전
//MOVE 2
//TURN 0
//MOVE 5
//TURN 1
//MOVE 5
//TURN 1
//MOVE 2
//TURN 1
//MOVE 3
//TURN 0
//TURN 0
//MOVE 6
//답 : 7 10
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int dxy[][] = {{0, 1},{-1, 0},{0, -1},{1, 0}};
	static int x = 0, y = 0, dir = 0;
	static int M, N;
	
	public static void  main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());// 한변의 길이(1<=1000)
		N = Integer.parseInt(st.nextToken());// 명령어 개수(1<=1000), 명령어가 유효하지 않으면 -1 출력
		while(N-->0)
		{
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			int v = Integer.parseInt(st.nextToken());
			
			if("TURN".equals(cmd))
			{
				dir += v == 0 ? -1 : 1;
				dir = (dir + 4) % 4;
				continue;
			}
			
			y += dxy[dir][0] * v;
			x += dxy[dir][1] * v;
			
			if(y<0 || x<0 || M<y || M<x)
			{
				System.out.print(-1);
				return;
			}
		}
		System.out.print(x + " " + y);
	}
}