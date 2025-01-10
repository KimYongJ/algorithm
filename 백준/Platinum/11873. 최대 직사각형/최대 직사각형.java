//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11873
class Main{
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			int Y = read();//1<=천
			int X = read();//1<=천
			int M = 0;
			if(Y == 0 && X == 0)
				break;
			
			int height[] = new int[X+1];
			for(int y=0; y<Y; y++)
			{
				for(int x=0; x<X; x++)
				{
					int n = read();//1<=천
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
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}