//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1090
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Location{int x,y;Location(int x, int y){this.x=x; this.y=y;}}

class Main{
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N				= Integer.parseInt(st.nextToken());
		int result[]		= new int[N];
		Location[] location	= new Location[N];
		
		Arrays.fill(result, 1<<30);
		
		for(int i=0; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			location[i] = new Location(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		for(int i=0; i<N; i++)
		{
			for(int j=0; j<N; j++)
			{
				int cost[] = new int[N];	// 각 체커마다 정해진 x,y좌표로 이동하는데 드는 비용
				for(int n=0; n<N; n++)
					cost[n] = Math.abs(location[i].x - location[n].x) + Math.abs(location[j].y - location[n].y);
				// 비용 오름차순 정렬
				Arrays.sort(cost);
				// 각 비용을 0~N명이 모두 이동할 때 까지 더해서 최소 값을 result에 담는다.
				int res = 0;
				for(int n=0; n<N; n++)
					result[n] = Math.min(result[n],res += cost[n]);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int r : result)
			sb.append(r).append(' ');
		System.out.print(sb);
	}
}