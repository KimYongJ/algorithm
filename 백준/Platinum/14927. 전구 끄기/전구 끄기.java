//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14927
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int min = 1<<30;
	static int N;
	static int origin[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N		= Integer.parseInt(br.readLine());
		origin	= new int[N];

		for(int y=0; y<N; y++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int x=0; x<N; x++)
				if("1".equals(st.nextToken()))
					origin[y] |= 1<<x;
		}
		int len = 1<<N;
		for(int first=0; first<len; first++)
		{
			int cnt		= Integer.bitCount(first);
			int map[]	= origin.clone();
			
			// 첫행 클릭
			for(int x=0; x<N; x++)
			{
				int bit = 1<<x;
				if((first & bit) != 0)
				{
					map[0] ^= bit;
					map[1] ^= bit;
					if(x+1 < N)
						map[0] ^= bit<<1;
					if(0< x)
						map[0] ^= bit>>1;
				}
			}
			
			// 나머지 클릭
			for(int y=1; y<N; y++)
			{
				for(int x=0; x<N; x++)
				{
					int bit = 1<<x;
					if((map[y-1] & bit) != 0)
					{
						map[y-1] ^= bit;
						map[y] ^= bit;
						if(y+1<N)
							map[y+1] ^= bit;
						if(x<N-1)
							map[y] ^= bit<<1;
						if(0<x)
							map[y] ^= bit>>1;
						
						++cnt;
					}
				}
			}
	        
			if(map[N-1] == 0)
				min = Math.min(min, cnt);
		}
		
		if(min == 1<<30)
			min = -1;
		System.out.print(min);
	}
}