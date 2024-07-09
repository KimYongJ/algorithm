// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		PriorityQueue<Integer> pq;
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		int J, N, cnt;
		
		while(T-->0)
		{
			pq	= new PriorityQueue<Integer>((a,b)->b-a);
			st	= new StringTokenizer(br.readLine());
			J	= Integer.parseInt(st.nextToken()); // 사탕 개수
			N	= Integer.parseInt(st.nextToken()); // 상자의 개수
			while(N-->0) 
			{
				st = new StringTokenizer(br.readLine());
				pq.add(Integer.parseInt(st.nextToken()) * Integer.parseInt(st.nextToken()));
			}
			cnt = 0;
			while(J>0) 
			{
				J -= pq.poll();
				cnt++;
			}
			sb.append(cnt).append('\n');
		}
		System.out.print(sb);
	}
}