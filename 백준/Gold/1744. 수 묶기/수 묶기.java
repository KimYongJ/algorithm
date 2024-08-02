// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pos = new PriorityQueue<Integer>((a,b)->b-a);
		PriorityQueue<Integer> neg = new PriorityQueue<Integer>();
		int N = Integer.parseInt(br.readLine());
		int sum = 0;
		int one = 0;
		int zero = 0;
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num == 0) zero++;
			else if(num == 1) one++;
			else if(num > 0) pos.add(num);
			else neg.add(num);
		}
		if(neg.size() > 0) {
			while(neg.size()>1) {
				sum += neg.poll() * neg.poll();
			}
			if(zero == 0 && !neg.isEmpty())
				sum += neg.poll();
		}
		if(pos.size() > 0) {
			while(pos.size()>1) {
				sum += pos.poll() * pos.poll();
			}
			if(pos.size() == 1)
				sum += pos.poll();
		}
		System.out.print(sum + one);
	}
}