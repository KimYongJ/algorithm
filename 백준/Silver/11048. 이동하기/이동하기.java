//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11048
//1초 / 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int Y = Integer.parseInt(st.nextToken());// 1<=천
		int X = Integer.parseInt(st.nextToken());// 1<=천
		int map[][] = new int[Y+1][X+1];
		for(int y=1; y<=Y; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=X; x++)
			{
				int a = map[y-1][x];
				int b = map[y][x-1];
				int c = map[y-1][x-1];
				map[y][x] = Integer.parseInt(st.nextToken())
						+ Math.max(a, Math.max(b, c)); 
			}
		}
		System.out.print(map[Y][X]);
	}
}