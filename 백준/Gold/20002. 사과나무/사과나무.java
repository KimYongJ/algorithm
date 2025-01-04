//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/20002
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		int map[][] = new int[N+2][N+2];
		
		for(int y=1; y<=N; y++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int x=1; x<=N; x++)
				map[y][x] = Integer.parseInt(st.nextToken()) + map[y][x-1] + map[y-1][x] - map[y-1][x-1];
		}

		int max = ~(1<<30);

		for(int len = 0; len<N; len++)
		{
			for(int y=1; y+len<=N; y++)
			{
				for(int x=1; x+len<=N; x++)
				{
					int y1 = y+len;
					int x1 = x+len;
					max = Math.max(max, map[y1][x1] - map[y1][x-1] - map[y-1][x1] + map[y-1][x-1]);
				}
			}
		}

		System.out.print(max);
	}
}
