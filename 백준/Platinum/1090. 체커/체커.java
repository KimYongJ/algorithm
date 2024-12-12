//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1090
import java.util.Arrays;

class Location{int x,y;Location(int x, int y){this.x=x; this.y=y;}}

class Main{
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N				= read();
		int result[]		= new int[N];
		Location[] location	= new Location[N];
		
		Arrays.fill(result, 1<<30);
		
		for(int i=0; i<N; i++)
			location[i] = new Location(read(), read());
		
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
			{
				int cost[] = new int[N];// 각 체커마다 정해진 x,y좌표로 이동하는데 드는 비용
				for(int n=0; n<N; n++)
					cost[n] = Math.abs(location[i].x - location[n].x) + Math.abs(location[j].y - location[n].y);
				// 비용 오름차순 정렬
				Arrays.sort(cost);
				// 각 비용을 0~N명이 모두 이동할 때 까지 더해서 최소 값을 result에 담는다.
				int res = 0;
				for(int n=0; n<N; n++)
					result[n] = Math.min(result[n],res += cost[n]);
			}
		
		StringBuilder sb = new StringBuilder();
		for(int r : result)
			sb.append(r).append(' ');
		System.out.print(sb);
	}
}
