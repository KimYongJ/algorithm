//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14727
//2초 / 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());	// 1<=십만
		long arr[]	= new long[N+1];
		long max	= 0;
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(br.readLine());
		
		Stack<Integer> stack = new Stack<>();
		for(int i=0; i<=N; i++)
		{
			while(!stack.isEmpty() && arr[i] < arr[stack.peek()])
			{
				long H = arr[stack.pop()];
				long W = i;
				if(!stack.isEmpty())
					W = i - stack.peek() - 1;
				max = Math.max(max, H * W);
			}
			stack.add(i);
		}
		System.out.print(max);
	}
}