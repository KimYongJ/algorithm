//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14391
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int max, map[];
	static int Y, X;
	
	public static void cal(int bitmask) {
		int result = 0;
		// 가로 탐색
		for(int y=0,i=0; y<Y; y++)
		{
			int tmp = 0;
			for(int x=0; x<X; x++,i++)
			{
				if((bitmask & (1<<i)) > 0)
					tmp = tmp * 10 + map[i];
				else
				{
					result += tmp;
					tmp = 0;
				}
			}
			result += tmp;
		}
		// 세로 탐색
		for(int x=0; x<X; x++) {
			int tmp = 0;
			for(int y=0,i=x; y<Y; y++,i+=X)
			{
				if((bitmask &(1<<i)) == 0)
					tmp = tmp * 10 + map[i];
				else {
					result += tmp;
					tmp = 0;
				}
			}
			result += tmp;
		}
		
		max = Math.max(max,  result);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y		= Integer.parseInt(st.nextToken()); // 1<=4
		X		= Integer.parseInt(st.nextToken()); // 1<=4
		map		= new int[Y*X];
		
		
		for(int y=0, i = 0; y<Y; y++)
		{
			String str = br.readLine();
			for(int x=0; x<X; x++)
				map[i++] = str.charAt(x) - '0';
		}
		
		int len = (1<<Y*X);
		
		for(int i=0; i<len; i++)
			cal(i);
		
		System.out.print(max);
	}
}