// https://github.com/kimyongj/algorithm
import java.util.PriorityQueue;
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a,b)->b-a);
		int N	= read();
		int res = -1;
		
		for(int i=0; i<N; i++)
			pq.add(read());
		
		int a = pq.poll();
		int b = pq.poll();
		int c = pq.poll();
		if(a < b+c) {
			res = a+b+c;
		}else {
			while(!pq.isEmpty()) {
				a = b;
				b = c;
				c = pq.poll();
				if(a < b+c) {
					res = a+b+c;
					break;
				}
			}
		}
		
		System.out.print(res);
	}
}