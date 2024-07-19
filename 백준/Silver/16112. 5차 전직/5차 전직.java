//https://github.com/KimYongJ/algorithm
import java.util.PriorityQueue;
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int N		= read();
		int K		= read();
		long k		= 1;
		long sum	= 0;

		for(int i=0; i<N; i++) 
			pq.add(read());
		
        pq.poll(); // 하나 버림
        
		for(int i=1; i<N; i++) 
		{
			sum += pq.poll() * k;
			if(k<K) 
				k++;
		}
		System.out.print(sum);
	}
}