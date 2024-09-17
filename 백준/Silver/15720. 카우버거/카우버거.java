//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/15720
import java.util.PriorityQueue;
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static int input(PriorityQueue<Integer> pq, int len)throws Exception{
		int sum = 0, n;
		for(int i=0; i<len; i++)
		{
			sum += n = read();
			pq.add(n);
		}
		return sum;
	}
	public static void main(String[] args)throws Exception{
		PriorityQueue<Integer> bq = new PriorityQueue<Integer>((a,b)->b-a);
		PriorityQueue<Integer> cq = new PriorityQueue<Integer>((a,b)->b-a);
		PriorityQueue<Integer> dq = new PriorityQueue<Integer>((a,b)->b-a);
		
		int B		= read();	// 버거 개수(1<=1000)
		int C		= read();	// 사이드 개수(1<=1000)
		int D		= read();	// 음료 개수(1<=1000)
		int sum1	= 0;
		int minus	= 0;
		
		sum1 += input(bq, B);
		sum1 += input(cq, C);
		sum1 += input(dq, D);

		while(!bq.isEmpty() && !cq.isEmpty() && !dq.isEmpty())
			minus += (bq.poll() + cq.poll() + dq.poll()) / 10;
		
		System.out.print( new StringBuilder().append(sum1).append('\n').append(sum1 - minus) );
	}
}