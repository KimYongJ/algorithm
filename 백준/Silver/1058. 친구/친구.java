//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/1058

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{

	static int N;
	static boolean map[][];
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N	= Integer.parseInt(br.readLine());
		map = new boolean[N][N];
		
		for(int y=0; y<N; y++)
		{
			String str = br.readLine();
			for(int x=0; x<N; x++)
				map[y][x] = map[x][y] = str.charAt(x) == 'Y';
		}
		
		int res = 0;
		for(int y=0; y<N; y++)
		{
			int cnt = 0;
			for(int x=0; x<N; x++)
				if(y==x)
					continue;
				else if(map[y][x])
					++cnt;
				else if(isFriend(y, x))
					++cnt;

			res = Math.max(res, cnt);
		}
		System.out.print(res);
	}
	public static boolean isFriend(int y, int x) {
		for(int i=0; i<N; i++) {
			if(map[i][x] && map[y][i])
				return true;
		}
		return false;
	}
}