// https://github.com/kimyongj/algorithm
import java.util.PriorityQueue;
class Main{
	public static int read() throws Exception {
		int c, n = System.in.read() & 15;
		boolean isNegative = n == 13;
		if (isNegative) n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13) System.in.read();
		return isNegative ? ~n + 1 : n;
	}
	public static void main(String[] args)throws Exception{
		PriorityQueue<Integer> pos = new PriorityQueue<Integer>((a,b)->b-a);
		PriorityQueue<Integer> neg = new PriorityQueue<Integer>((a,b)->b-a);
		int N = read();
		int M = read();
		int n,max = 0;
		for(int i=0; i<N; i++) 
		{
			n = read();
			if(n< 0) 
			{
				n = -n;
				neg.add(n);
			}
			else
				pos.add(n);
			
			if(max < n)
				max = n;
		}
		
		int sum = -max;
		int pSize = pos.size();
		int nSize = neg.size();
		for(int i=0; i<pSize; i++) 
		{
			n = pos.poll();
			if(i % M == 0)
				sum += n<<1;
		}
		for(int i=0; i<nSize; i++) 
		{
			n = neg.poll();
			if(i % M == 0)
				sum += n<<1;
		}
		
		System.out.print(sum);
	}
}