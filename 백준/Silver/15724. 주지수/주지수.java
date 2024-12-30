//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/15724
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder	sb = new StringBuilder();
		
		int Y		= Integer.parseInt(st.nextToken());	// 1<=1024
		int X		= Integer.parseInt(st.nextToken());	// 1<=1024
		int map[][] = new int[Y+2][X+2];
		
		for(int y=1; y<=Y; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=X; x++)
				map[y][x] = Integer.parseInt(st.nextToken()) + map[y-1][x] + map[y][x-1] - map[y-1][x-1];
		}
		int K = Integer.parseInt(br.readLine());
		while(K-->0)
		{
			st = new StringTokenizer(br.readLine());
			int y1 = Integer.parseInt(st.nextToken());
			int x1 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			sb.append(map[y2][x2]-map[y2][x1-1]-map[y1-1][x2]+map[y1-1][x1-1]).append('\n');
		}
		System.out.print(sb);
	}
}