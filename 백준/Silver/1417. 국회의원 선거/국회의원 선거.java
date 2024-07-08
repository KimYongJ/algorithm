// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br			= new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pq	= new PriorityQueue<Integer>((a,b)->b-a);
		int N		= Integer.parseInt(br.readLine());
		int dasom	= Integer.parseInt(br.readLine());
		int cnt		= 0;
		
		for(int i=1; i<N; i++) 
		{
			pq.add(Integer.parseInt(br.readLine()));
		}
		if(pq.size() > 0) {
			while(dasom <= pq.peek()) 
			{
				pq.add(pq.poll() - 1);
				dasom++;
				cnt++;
			}
		}
		System.out.print(cnt);
	}
}