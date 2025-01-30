//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/12846
//1초 / 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		long arr[]	= new long[N+1];
		long max	= 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		int stack[]	 = new int[N+1];
		int stackIdx = -1;
		for(int i=0; i<=N; i++)
		{
			while(0<=stackIdx && arr[i] < arr[stack[stackIdx]])
			{
				long H = arr[stack[stackIdx--]];
				long W = i;
				if(0<=stackIdx)
					W = i - stack[stackIdx] - 1;
				max = Math.max(max, H * W);
			}
			stack[++stackIdx] = i;
		}
		
		System.out.print(max);
	}
}