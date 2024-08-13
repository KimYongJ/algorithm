// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0) 
		{
			PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a,b)->b-a);
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++)
				pq.add(Integer.parseInt(st.nextToken()));
			
			int left = pq.poll();
			int right = left;
			boolean flag = true;
			int MAX = 0;
			while(!pq.isEmpty()) 
			{
				int now = pq.poll();
				if(flag) {
					MAX = Math.max(MAX, Math.abs(now-left));
					left = now;
				}else {
					MAX = Math.max(MAX, Math.abs(now-right));
					right = now;
				}
				flag = !flag;
			}
			
			sb.append(Math.max(MAX, Math.abs(left-right))).append('\n');
		}
		System.out.print(sb.toString());
	}
}