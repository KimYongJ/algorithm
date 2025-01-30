//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1989
//2초 / 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	// 1<=십만
		long arr[] = new long[N+2];
		long psum[]= new long[N+2];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
		{
			arr[i] = Integer.parseInt(st.nextToken());
			psum[i] += psum[i-1] + arr[i];
		}
		long max= 0;
		int s	= 1;
		int e	= 1;
		
		Stack<Integer> stack = new Stack<>();
		
		for(int i=1; i<=N+1; i++)
		{
			while(!stack.isEmpty() && arr[i] < arr[stack.peek()])
			{
				int ee = stack.pop();
				int ss = 0;
				if(!stack.isEmpty())
					ss = stack.peek();
				
				long min = arr[ee];
				long sum = psum[i - 1] - psum[ss];
				if(max < min * sum) {
					max = min * sum;
					s = ss + 1;
					e = i - 1;
				}
			}
			stack.add(i);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(max).append('\n').append(s).append(' ').append(e);
		System.out.print(sb);
	}
}