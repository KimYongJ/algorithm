//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14620
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static boolean visit[][];
	static int N, map[][];
	static int result = 1<<30;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N		= Integer.parseInt(br.readLine());
		map		= new int[N][N];
		visit	= new boolean[N][N];
		for(int y=0; y<N; y++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int x=0; x<N; x++)
				map[y][x] = Integer.parseInt(st.nextToken());
		}
		
		bruteforce(0, 1, 0);
		
		System.out.print(result);
	}
	public static void bruteforce(int depth, int y, int sum) {
		if(depth == 3) {
			result = Math.min(result, sum);
			return;
		}
		for(; y<N-1; y++)
		{
			for(int x = 1; x<N-1; x++)
			{
				if(!visit[y][x+1] && !visit[y][x] && !visit[y][x-1] && !visit[y+1][x] && !visit[y-1][x])
				{
					visit[y][x+1] = visit[y][x] = visit[y][x-1] = visit[y+1][x] = visit[y-1][x] = true;
					bruteforce(depth + 1, y, sum + map[y][x+1] + map[y][x] + map[y][x-1] + map[y+1][x] + map[y-1][x]);
					visit[y][x+1] = visit[y][x] = visit[y][x-1] = visit[y+1][x] = visit[y-1][x] = false;
				}
			}
		}
	}
}