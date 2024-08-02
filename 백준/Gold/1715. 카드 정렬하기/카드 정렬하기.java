// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int N = Integer.parseInt(br.readLine());
		if(N == 1 ) {
			System.out.print(0);
			return;
		}
		for(int i=0; i<N; i++) {
			pq.add(Integer.parseInt(br.readLine()));
		}
		long result = 0;
		while(!pq.isEmpty()) {
			int a = pq.poll();
			int b = pq.poll();
			result += a+b;
			if(pq.isEmpty()) break;
			pq.add(a+b);
		}
		System.out.print(result);
	}
}