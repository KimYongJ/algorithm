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
		PriorityQueue<Integer> neg = new PriorityQueue<Integer>();
		int N	= read();
		int sum = 0;
		int one = 0;
		int zero = 0;
		for(int i=0; i<N; i++) 
		{
			int num = read();
			if(num == 0) 
				zero++;
			else if(num == 1) 
				one++;
			else if(num > 0) 
				pos.add(num);
			else 
				neg.add(num);
		}

		while(neg.size()>1) sum += neg.poll() * neg.poll();
		while(pos.size()>1) sum += pos.poll() * pos.poll();
		
		if(zero == 0 && !neg.isEmpty())
			sum += neg.poll();
		
		if(pos.size() == 1)
			sum += pos.poll();
		
		System.out.print(sum + one);
	}
}