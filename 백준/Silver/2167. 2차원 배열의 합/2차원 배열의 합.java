//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2167
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int Y		= Integer.parseInt(st.nextToken());
		int X		= Integer.parseInt(st.nextToken());
		int psum[][]= new int[Y+2][X+2];
		
		for(int y=1; y<=Y; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=X; x++)
				psum[y][x] += psum[y][x-1] + psum[y-1][x] - psum[y-1][x-1] + Integer.parseInt(st.nextToken());
		}
		
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		while(N-->0)
		{
			st = new StringTokenizer(br.readLine());
			int y1 = Integer.parseInt(st.nextToken());
			int x1 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			sb.append(psum[y2][x2] - psum[y2][x1-1] - psum[y1-1][x2] + psum[y1-1][x1-1]).append('\n');
		}
		System.out.print(sb);
	}
}
