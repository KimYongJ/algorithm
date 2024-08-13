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
		int N = read(); // 달걀 개수
		int M = read(); // 고객수
		
		for(int i=0; i<M; i++)
			pq.add(read());
		
		int price	= 0;
		int cnt		= 0;
		int total	= 0;
		for(int i=1; i<=M && i<= N; i++)
		{
			int nowPrice = pq.poll();
			if(total < nowPrice*i) 
			{
				price = nowPrice;
				cnt = i;
				total = price * cnt;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(price).append(' ').append(total);
		System.out.print(sb.toString());
	}
}