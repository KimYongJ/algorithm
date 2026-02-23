//https://www.acmicpc.net/problem/13268
//1초 128MB
//입력 : 7 // 달릴 수 있는 거리 (1<=10,000)
//답 : 1 // 시작점 부터 첫번째 콘까지 구간 1
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		final int dist[] = {5, 10, 15, 20};
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int remain = Integer.parseInt(br.readLine()) % 100;

		int now = 0;
		int idx = 0;
		boolean forward = false;
		
		while(remain > 0)
		{
			int move = Math.min(remain, dist[idx]);
			remain -= move;
			forward = !forward;
			
			if(forward)
			{
				now += move;
				continue;
			}

			now -= move;
			idx = (idx+1) % 4;			
		}
		
		int res = 0;
		
		if(now != 0)
		{
			if(now <= 5) res = 1;
			else if(now <= 10) res = 2;
			else if(now <= 15) res = 3;
			else res = 4;
		}
		System.out.print(res);
	}
}