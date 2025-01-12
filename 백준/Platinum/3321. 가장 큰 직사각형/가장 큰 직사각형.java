//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/3321
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int Y			= Integer.parseInt(st.nextToken());
		int X			= Integer.parseInt(st.nextToken());
		int	height[]	= new int[X];
		int heightCopy[]= new int[X];
		int stack[]		= new int[X];
		int max			= 0;

		for(int y=0; y<Y; y++)
		{
			String str = br.readLine();
			for(int x=0; x<X; x++)
			{
				if(str.charAt(x) == '1')
					height[x]++;
				else
					height[x] = 0;
				
				heightCopy[x] = height[x];
			}

			Arrays.sort(heightCopy);
			
			max = Math.max(max,getArea(heightCopy, stack, X));
		}
		
		System.out.print(max);
	}
	public static int getArea(int height[], int stack[], int len) {
		int max		= 0;
		int stIdx	= -1;
		for(int i=0; i<=len; i++)
		{
			int h = i==len ? 0 : height[i];
			while(0 <= stIdx && height[stack[stIdx]] > h)
			{
 				int H = height[stack[stIdx--]];
				int W = i;
				if(0 <= stIdx)
					W = i - stack[stIdx] - 1;
				
				max = Math.max(max, W*H);
			}
			
			stack[++stIdx] = i;
		}
		return max;
	}
}