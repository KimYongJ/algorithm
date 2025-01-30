//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1989
//2초 / 128MB

class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();	// 1<=십만
		long arr[]	= new long[N+2];
		long psum[]	= new long[N+2];
		
		for(int i=1; i<=N; i++)
		{
			arr[i] = read();	// 0<백만
			psum[i] += psum[i-1] + arr[i];
		}
		long max= 0;
		int s	= 1;
		int e	= 1;
		
		Stack stack = new Stack(N+2);
		
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
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}
class Stack{
	int N, stackIdx;
	int arr[];
	Stack(int N){
		this.N = N;
		this.stackIdx = -1;
		this.arr = new int[N];
	}
	boolean isEmpty() {return stackIdx < 0;}
	int peek() {return arr[stackIdx];}
	int add(int i) {return arr[++stackIdx] = i;}
	int pop() {return arr[stackIdx--];}
}