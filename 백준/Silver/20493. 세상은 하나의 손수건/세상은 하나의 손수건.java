//https://www.acmicpc.net/problem/20493
//1초 1024MB
//3 100 // 방향 바꾼 횟수(0<=100,000), 걸어간 시간(1<=1,000,000,000)
//30 right // i번째로 방향을 바꾼 시간, 회전 방향
//50 right // 시간은 겹치지 않으며, 오름차순으로 주어진다.
//60 left
//답 : 20 -60
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		final int dxy[][] = {{0,1},{1,0},{0,-1},{-1,0}};
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int y = 0;
		int x = 0;
		int idx = 0;
		int nowTime = 0;
		
		while(N-->0)
		{
			st = new StringTokenizer(br.readLine());
			int targetTime = Integer.parseInt(st.nextToken());
			int timeDiff = targetTime - nowTime;
			
			y += dxy[idx][0] * timeDiff;
			x += dxy[idx][1] * timeDiff;
			
			idx = nextIdx(idx, st.nextToken().charAt(0));
			nowTime = targetTime;
		}
		
		int timeDiff = T - nowTime;
		
		y += dxy[idx][0] * timeDiff;
		x += dxy[idx][1] * timeDiff;
		
		System.out.printf("%d %d", x, y);
	}
	static int nextIdx(int idx, char cmd) {
		if(cmd == 'r') return (idx + 3) % 4;
		return (idx + 1) % 4;
	}
}