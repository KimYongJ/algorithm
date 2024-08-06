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
		PriorityQueue<Integer> neg = new PriorityQueue<>();
		int N = read();
		boolean zero = false;
		long sum = 0;
		while(N-->0) {
			int n = read();
			if(n == 0) zero = true;
			else if(n == 1) sum++;
			else if(n > 0) pos.add(n);
			else neg.add(n);
		}
		while(pos.size() > 1) sum += (long)pos.poll() * pos.poll();
		while(neg.size() > 1) sum += (long)neg.poll() * neg.poll();
		
		if(pos.size() == 1) sum += pos.poll();
		if(!zero && neg.size() == 1) sum += neg.poll();
		
		System.out.print(sum);
	}
}
