// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		while(T-->0) {
			PriorityQueue<Long> pq = new PriorityQueue<>();
			long sum = 0;
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			while(N-->0)
				pq.add(Long.parseLong(st.nextToken()));
			while(pq.size() > 1) 
			{
				long n = pq.poll() + pq.poll();
				sum += n;
				pq.add(n);
			}
			sb.append(sum).append('\n');
		}
		System.out.print(sb.toString());
	}
}
