// https://github.com/kimyongj/algorithm
import java.util.PriorityQueue;
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		PriorityQueue<Long> pq = new PriorityQueue<>();
		int N = read();
		int M = read();
		while(N-->0) {
			pq.add((long)read());
		}
		while(M-->0) {
			long n1 = pq.poll();
			long n2 = pq.poll();
			pq.add(n1+n2);
			pq.add(n1+n2);
		}
		long sum = 0;
		while(!pq.isEmpty()) {
			sum += pq.poll();
		}
		System.out.print(sum);
	}
}