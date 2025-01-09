//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/6549
// 1초 / 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());	// 직사각형의 수(1<=십만)
			if(N == 0)
				break;
			
			int height[] = new int[N];
			for(int i=0; i<N; i++)
				height[i] = Integer.parseInt(st.nextToken());// 0<=십억

			sb.append(getArea(height)).append('\n');
		}
		System.out.print(sb);
	}
	public static long getArea(int[] height) {
		long max		= 0;
		int len			= height.length;
		int stack[]		= new int[len+1];
		int stackIdx	= -1;
		for(int i=0; i<=len; i++)
		{
			int h = i == len ? 0 : height[i];
			while(0<=stackIdx && height[stack[stackIdx]] > h)
			{
				long H = height[stack[stackIdx--]];
				long W = i;
				if(0<=stackIdx)
					W = i - stack[stackIdx] - 1;
				
				max = Math.max(max, H*W);
			}
			stack[++stackIdx] = i;
		}
		return max;
	}
}