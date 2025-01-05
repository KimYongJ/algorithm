//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14846

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N			= Integer.parseInt(br.readLine());
		int map[][][]	= new int[11][N+2][N+2];
		
		for(int y=1; y<=N; y++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int x=1; x<=N; x++)
			{
				int idx = Integer.parseInt(st.nextToken());
				map[idx][y][x] = 1;
				
				for(int i=1; i<=10; i++)
					map[i][y][x] += map[i][y-1][x] + map[i][y][x-1] - map[i][y-1][x-1];
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int Q = Integer.parseInt(br.readLine());
		while(Q-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int y1 = Integer.parseInt(st.nextToken());
			int x1 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int cnt = 0;
			
			for(int i=1; i<=10; i++)
				if(0<map[i][y2][x2] - map[i][y2][x1-1] - map[i][y1-1][x2] + map[i][y1-1][x1-1])
					++cnt;
			
			sb.append(cnt).append('\n');
		}
		System.out.print(sb);
	}
}