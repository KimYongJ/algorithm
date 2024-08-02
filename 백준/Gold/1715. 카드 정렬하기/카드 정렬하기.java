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
		int res	= 0;
		int N	= read();

		for(int i=0; i<N; i++)
			pq.add(read());
	
		
		while(pq.size() > 1) 
		{
			int a = pq.poll();
			int b = pq.poll();
			
			res += a+b;
			
			pq.add(a+b);
		}
		System.out.print(res);
	}
}