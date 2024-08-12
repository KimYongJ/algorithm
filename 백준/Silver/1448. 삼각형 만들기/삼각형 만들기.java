// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
class Main{
	public static void main(String[] args)throws Exception{
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a,b)->b-a);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N	= Integer.parseInt(br.readLine());
		int res = -1;
		
		for(int i=0; i<N; i++)
			pq.add(Integer.parseInt(br.readLine()));
		
		int a = pq.poll();
		int b = pq.poll();
		int c = pq.poll();
		if(a < b+c) {
			res = a+b+c;
		}else {
			while(!pq.isEmpty()) {
				a = b;
				b = c;
				c = pq.poll();
				if(a < b+c) {
					res = a+b+c;
					break;
				}
			}
		}
		
		System.out.print(res);
	}
}
