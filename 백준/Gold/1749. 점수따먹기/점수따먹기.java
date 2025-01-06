//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1749
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int Y		= Integer.parseInt(st.nextToken());
		int X		= Integer.parseInt(st.nextToken());
		int map[][] = new int[Y+2][X+2];
		
		for(int y=1; y<=Y; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=X; x++)
				map[y][x] = Integer.parseInt(st.nextToken()) + map[y-1][x] + map[y][x-1] - map[y-1][x-1];
		}
		
		int ans = ~(1<<30);
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				for(int y1=0; y1<y; y1++)
					for(int x1=0; x1<x; x1++)
						ans = Math.max(ans, map[y][x] - map[y][x1] - map[y1][x] + map[y1][x1]);

		System.out.print(ans);
	}
}