//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/29718
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int Y		= Integer.parseInt(st.nextToken());
		int X		= Integer.parseInt(st.nextToken());
		int map[][] = new int[Y+2][X+2];
		
		for(int y=1; y<=Y; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=X; x++)
				map[y][x] = Integer.parseInt(st.nextToken()) + map[y][x-1] + map[y-1][x] - map[y-1][x-1];
		}
		
		int s	= Integer.parseInt(br.readLine());
		int max = 0;
		
		for(int x1=s,x2=0; x1<=X; x1++,x2++)
		{
			int sum = map[Y][x1] - map[Y][x2];
			if(max < sum)
				max = sum;
		}
		System.out.print(max);
	}
}