//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/18228
//2초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		int N = Integer.parseInt(br.readLine());
		int res = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			int now = Integer.parseInt(st.nextToken());
			if(now < 0)
			{
				if(!pq.isEmpty())
					res += pq.peek();
				pq.clear();
				continue;
			}
			pq.add(now);
			
			if(i == N && !pq.isEmpty()) {
				res += pq.peek();
			}
		}
		System.out.print(res);
	}

}