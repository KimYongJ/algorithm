//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2104
//2초 / 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Main{
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N	= Integer.parseInt(br.readLine());// 1<=십만
		long[] arr	= new long[N+2];
		long[] psum = new long[N+1];
		long res	= 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			psum[i] = arr[i] = Long.parseLong(st.nextToken());//0<=백만
			psum[i] += psum[i-1];
		}
		
		Stack<Integer> stack = new Stack<>();
		for(int i=1; i<=N+1; i++)
		{
			while(!stack.isEmpty() && arr[stack.peek()] > arr[i])
			{
				long H = arr[stack.pop()];
				int idx = 0;
				if(!stack.isEmpty())
					idx = stack.peek();
				
				long W = psum[i-1] - psum[idx];
				
				res = Math.max(res, H*W);
			}
			stack.push(i);
		}
		System.out.print(res);
	}
}