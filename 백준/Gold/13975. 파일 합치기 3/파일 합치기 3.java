// https://github.com/kimyongj/algorithm
import java.util.PriorityQueue;
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int T = read();
		while(T-->0) {
			PriorityQueue<Long> pq = new PriorityQueue<>();
			long n,sum	= 0;
			int N		= read();
			
			while(N-->0)
				pq.add((long)read());
			
			while(pq.size() > 1) 
			{
				n = pq.poll() + pq.poll();
				sum += n;
				pq.add(n);
			}
			sb.append(sum).append('\n');
		}
		System.out.print(sb.toString());
	}
}
