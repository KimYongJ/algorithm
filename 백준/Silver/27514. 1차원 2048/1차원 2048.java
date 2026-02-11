//https://www.acmicpc.net/problem/27514
//1초 1024MB
//20
//512 32 64 0 0 0 0 64 64 0 32 0 0 0 512 0 0 256 256 256
//답 : 2048
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Long> pq = new PriorityQueue<>();
		int N = Integer.parseInt(br.readLine());
		long max = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		while(N-->0)
			pq.add(Long.parseLong(st.nextToken()));
		
		while(pq.size() > 1)
		{
			long now = pq.poll();
			
			if(pq.peek() == now)
			{
				pq.poll();
				pq.add(now *= 2);
			}

			max = Math.max(max, now);
		}
		
		while(!pq.isEmpty())
			max = Math.max(max, pq.poll());
		
		System.out.print(max);
	}
}