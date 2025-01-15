//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2104
//2초 / 128MB
class Main{
	
	public static void main(String[] args)throws Exception{
		int N		= read();// 1<=십만
		long[] arr	= new long[N+2];
		long[] psum = new long[N+1];
		long res	= 0;
		
		for(int i=1; i<=N; i++)
		{
			psum[i] = arr[i] = read();//0<=백만
			psum[i] += psum[i-1];
		}
		
		Stack stack = new Stack();
		stack.push(0);
		for(int i=1; i<=N+1; i++)
		{
			while(!stack.isEmpty() && arr[stack.peek()] > arr[i])
			{
				long H = arr[stack.pop()];
				int idx = stack.peek();
				
				long W = psum[i-1] - psum[idx];
				
				res = Math.max(res, H*W);
			}
			stack.push(i);
		}
		System.out.print(res);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}
class Stack{
	Node node;
	int size = 0;
	public boolean isEmpty() {return size == 0;}
	public int peek() {return node.value;}
	public void push(int value) {++size;node = new Node(value, node);}
	public int pop() {
		int value = node.value;
		node = node.next;
		--size;
		return value;
	}
}

class Node{int value;Node next;Node(int v, Node n){value=v; next=n;}}