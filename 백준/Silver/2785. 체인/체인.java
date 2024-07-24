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
		int N	= read();
		int sum	= 0;
		int cnt = 1;
		
		for(int i=0; i<N; i++) 
			pq.add(read());

		
		for(int i=0; i<N && sum < cnt; i++) 
		{
			sum += pq.poll();	// 지금까지 만든 체인개수
			cnt = N - 1 - i;	// 특정 인덱스일 때 남은 체인 개수
		}
		System.out.print(cnt);
	}
}