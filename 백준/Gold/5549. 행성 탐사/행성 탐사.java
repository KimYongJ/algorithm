//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/5549
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int Y = Integer.parseInt(st.nextToken());	// 1<=천
		int X = Integer.parseInt(st.nextToken());	// 1<=천
		int K = Integer.parseInt(br.readLine());	// 1<=십만
		int psum[][][] = new int[3][Y+2][X+2];
		
		for(int y=1; y<=Y; y++)
		{
			String str = br.readLine();
			for(int x=1; x<=X; x++)
			{
				int idx = 0;
				switch(str.charAt(x-1))
				{
				case 'J': idx = 0; break;
				case 'O': idx = 1; break;
				case 'I': idx = 2; break;
				}
				
				psum[idx][y][x] = 1;
				
				psum[0][y][x] += psum[0][y-1][x] + psum[0][y][x-1] - psum[0][y-1][x-1];
				psum[1][y][x] += psum[1][y-1][x] + psum[1][y][x-1] - psum[1][y-1][x-1];
				psum[2][y][x] += psum[2][y-1][x] + psum[2][y][x-1] - psum[2][y-1][x-1];
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while(K-->0)
		{
			st = new StringTokenizer(br.readLine());
			int y1 = Integer.parseInt(st.nextToken());
			int x1 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int a = psum[0][y2][x2] - psum[0][y2][x1-1] - psum[0][y1-1][x2] + psum[0][y1-1][x1-1];
			int b = psum[1][y2][x2] - psum[1][y2][x1-1] - psum[1][y1-1][x2] + psum[1][y1-1][x1-1];
			int c = psum[2][y2][x2] - psum[2][y2][x1-1] - psum[2][y1-1][x2] + psum[2][y1-1][x1-1];
			sb.append(a).append(' ').append(b).append(' ').append(c).append('\n');
		}
		System.out.print(sb);
	}
}
