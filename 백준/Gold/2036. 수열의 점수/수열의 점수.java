// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pos = new PriorityQueue<Integer>((a,b)->b-a);
		PriorityQueue<Integer> neg = new PriorityQueue<>();
		int N = Integer.parseInt(br.readLine());
		boolean zero = false;
		long sum = 0;
		while(N-->0) {
			int n = Integer.parseInt(br.readLine());
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