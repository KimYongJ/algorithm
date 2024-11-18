//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1034
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int Y			= Integer.parseInt(st.nextToken());	// Y(1<=50)
		int X			= Integer.parseInt(st.nextToken());	// X(1<=50)
		int zeroCnt[]	= new int[Y];
		boolean map[][]	= new boolean[Y][X];
		
		for(int y=0; y<Y; y++)
		{
			String str = br.readLine();
			for(int x=0; x<X; x++) {
				map[y][x] = str.charAt(x) == '1';
				if(!map[y][x])
					++zeroCnt[y];
			}
		}
		
		int K	= Integer.parseInt(br.readLine());	// 누르는횟수(0<=천)
		int res = 0;
		
		for(int y=0; y<Y; y++)
			if(zeroCnt[y] <= K && (K-zeroCnt[y]) % 2 == 0)
			{
				int cnt = 1;
				for(int y1=0; y1<Y; y1++)
				{
					if(y == y1)
						continue;
					boolean flag = true;
					for(int x=0; x<X; x++)
					{
						if(map[y][x] != map[y1][x])
						{
							flag = false;
							break;
						}
					}
					if(flag)
						++cnt;
				}
				res = Math.max(res, cnt);
			}
		
		System.out.print(res);
	}
}
