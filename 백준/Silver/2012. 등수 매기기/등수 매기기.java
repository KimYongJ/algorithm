//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
class Main{
	public static void main(String[] args)throws Exception{
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim())+1;
		boolean visit[] = new boolean[N];
		for(int i=1; i<N; i++)
		{
			int n  = Integer.parseInt(br.readLine().trim());
			if(n < N && !visit[n]) visit[n] = true;
			else pq.add(n);
		}
		
		long res = 0;
		for(int i=1; i<N; i++)
			if(!visit[i])
				res += Math.abs(i-pq.poll());

		System.out.print(res);
	}
}