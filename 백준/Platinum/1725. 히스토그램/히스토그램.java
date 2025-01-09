//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1725
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		int max		= 0;
		int height[]= new int[N+1];
		int stack[]	= new int[N+1];
		int stIdx	= -1;
		
		for(int i=0; i<N; i++)
			height[i] = Integer.parseInt(br.readLine());
		
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
}