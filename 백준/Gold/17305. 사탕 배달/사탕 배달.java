//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17305
import java.util.PriorityQueue;

class Main{
	public static void main(String[] args)throws Exception{
		PriorityQueue<Integer> g3 = new PriorityQueue<>((a,b)-> b-a);
		PriorityQueue<Integer> g5 = new PriorityQueue<>((a,b)-> b-a);
		int N = read();
		int W = read();

		for(int i=0; i<N; i++)
		{
			int g = read();
			int s = read();
			if(g == 3)
				g3.add(s);
			else
				g5.add(s);
		}
		
		long sum3[] = new long[g3.size() + 1];
		long sum5[] = new long[g5.size() + 1];
		int len3	= g3.size();
		int len5	= g5.size();
		
		for(int i=1; i<=len3; i++)
			sum3[i] = sum3[i-1] + g3.poll();
		for(int i=1; i<=len5; i++)
			sum5[i] = sum5[i-1] + g5.poll();
		
		long max = 0;
		
		for(int i=0; i<=len5; i++)
		{
			int weight5 = i * 5;
			if(W < weight5)
				break;
			
			int remainWeight = W - weight5;
			
			max = Math.max(max, sum5[i] + sum3[Math.min(remainWeight / 3, len3)]);
		}
		System.out.print(max);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}