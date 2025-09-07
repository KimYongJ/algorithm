//https://www.acmicpc.net/problem/2980
//1초 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());// 신호등개수 1<=100
		int L = Integer.parseInt(st.nextToken());// 도로길이 1<=1000
		int info[][] = new int[L + 1][2];
		
		for(int i=0; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int D = Integer.parseInt(st.nextToken());
			info[D][0] = Integer.parseInt(st.nextToken());// 빨간색 지속시간
			info[D][1] = Integer.parseInt(st.nextToken());// 초록색 지속시간
		}
		
		int time = 0;
		
		for(int i=0; i<=L; i++)
		{
			if(info[i][0] != 0)
			{
				time += waitTime(info[i], time);
			}
			++time;
		}
		
		System.out.print(time - 1);
	}
	static int waitTime(int[] info, int time) {
		// 현재 시간에서 몇초를 더 기다려야 초록으로 바뀌는가 ? 
		time = time % (info[0] + info[1]);
		// 5, 3 6이 남았다면? 
		if(time > info[0])
			return 0;
		
		return info[0] - time;
	}
}