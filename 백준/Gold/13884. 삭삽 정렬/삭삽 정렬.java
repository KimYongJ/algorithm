// https://github.com/kimyongj/algorithm
import java.util.PriorityQueue;
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		StringBuilder 	sb	= new StringBuilder();
		int T = read();
		while(T-->0) 
		{
			pq.clear();
			int K		= read();
			int N		= read();
			int R		= 0;
			int arr[]	= new int[N];
			
			for(int i=0; i<N; i++) 
			{
				arr[i] = read();
				pq.add(arr[i]);
			}
			
			for(int i=0; i<N; i++)
				if(!pq.isEmpty() && pq.peek() == arr[i]) pq.poll();
				else R++;
			
			sb.append(K).append(' ').append(R).append('\n');
		}
		System.out.print(sb.toString());
	}
}