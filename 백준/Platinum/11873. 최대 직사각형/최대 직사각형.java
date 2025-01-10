//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11873
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int Y = Integer.parseInt(st.nextToken());//1<=천
			int X = Integer.parseInt(st.nextToken());//1<=천
			int M = 0;
			if(Y == 0 && X == 0)
				break;
			
			int height[] = new int[X+1];
			for(int y=0; y<Y; y++)
			{
				st = new StringTokenizer(br.readLine());
				for(int x=0; x<X; x++)
				{
					int n = Integer.parseInt(st.nextToken());//1<=천
					if(n == 0)
						height[x] = 0;
					else
						height[x]++;
				}
				M = Math.max(M, getArea(height, X));
			}
			
			sb.append(M).append('\n');
		}
		System.out.print(sb);
	}
	public static int getArea(int[] height, int len) {
		int stack[] = new int[len+1];
		int stIdx	= -1;
		
		int max = 0;
		for(int i=0; i<=len; i++)
		{
			while(0<=stIdx && height[stack[stIdx]] > height[i])
			{
				int H = height[stack[stIdx--]];
				int W = i;
				if(0<=stIdx)
					W = i - stack[stIdx] - 1;
				max = Math.max(max, H*W);
			}
			stack[++stIdx] = i;
		}
		return max;
	}
}