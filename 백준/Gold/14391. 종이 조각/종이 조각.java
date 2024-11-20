//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14391
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int max, len, n[], map[][];
	static int Y, X;
	
	public static int get() {
		int result = 0;
		for(int y=0; y<Y; y++)
		{
			int sum = 0;
			for(int x=X-1,x1=0; x>=0; x--,x1++)
			{
				if((n[y] & (1<<x)) > 0)
					sum = sum * 10 + map[y][x1];
				else
				{
					result += sum;
					sum = 0;
				}
			}
			result += sum;// 마지막 연산
		}
		for(int x=X-1, x1=0; x>=0; x--,x1++)
		{
			int sum = 0;
			for(int y=0; y<Y; y++)
			{
				if((n[y] & (1<<x)) == 0)
					sum = sum * 10 + map[y][x1];
				else {
					result += sum;
					sum = 0;
				}
			}
			result += sum;
		}
		
		return result;
	}
	
	public static void bruteforce(int depth) {
		if(depth == Y)
		{
			max = Math.max(max, get());
			
			return;
		}
		for(n[depth] = 0; n[depth]<=len; n[depth]++)
			bruteforce(depth + 1);
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y	= Integer.parseInt(st.nextToken()); // 1<=4
		X	= Integer.parseInt(st.nextToken()); // 1<=4
		n	= new int[Y];
		map	= new int[Y][X];
		len	= (1<<X)-1;
		
		for(int y=0; y<Y; y++)
		{
			String str = br.readLine();
			for(int x=0; x<X; x++)
				map[y][x] = str.charAt(x) - '0';
		}
		
		bruteforce(0);
		
		System.out.print(max);
	}
}