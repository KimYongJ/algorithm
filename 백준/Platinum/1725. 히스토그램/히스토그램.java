//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1725
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();
		int max		= 0;
		int height[]= new int[N+1];
		int stack[]	= new int[N+1];
		int stIdx	= -1;
		
		for(int i=0; i<N; i++)
			height[i] = read();
		
  		for(int i=0; i<=N; i++)
		{
   			while(0<=stIdx && height[stack[stIdx]] > height[i])
			{
				int H = height[stack[stIdx--]];
				int W = i;
				if(0<=stIdx)
					W = i - stack[stIdx] - 1;
 				max = Math.max(H * W, max);
			}
			
			stack[++stIdx] = i;
		}
		System.out.print(max);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}