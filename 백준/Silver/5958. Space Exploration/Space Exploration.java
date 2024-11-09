//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/5958
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
class Main{
	public static void main(String[] args)throws Exception{
		final int dxy[][]	= {{1,0},{0,1},{-1,0},{0,-1}};
		ArrayDeque<int[]> q = new ArrayDeque<>();
		BufferedReader br	= new BufferedReader(new InputStreamReader(System.in));
		int N			= Integer.parseInt(br.readLine());
		int cnt			= 0;
		char map[][]	= new char[N][N];
		
		for(int y=0; y<N; y++)
			map[y] = br.readLine().toCharArray();
		
		for(int y=0; y<N; y++)
			for(int x=0; x<N; x++)
			{
				if(map[y][x] == '*')
				{
					++cnt;
					map[y][x] = 0;
					q.add(new int[] {y, x});
					while(!q.isEmpty())
					{
						int now[] = q.poll();
						for(int xy[] : dxy) {
							int nextY = now[0] + xy[0];
							int nextX = now[1] + xy[1];
							if(0<=nextY && 0<=nextX && nextY<N && nextX<N 
									&& map[nextY][nextX] == '*')
							{
								map[nextY][nextX] = 0;
								q.add(new int[] {nextY, nextX});
							}
								
						}
					}
				}
			}
		
		System.out.print(cnt);
	}
}