//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());
		int K		= Integer.parseInt(st.nextToken());
		long k		= 0;
		long sum	= 0;
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) 
		{
			pq.add(Integer.parseInt(st.nextToken()));
		}
		
		for(int i=0; i<N; i++) 
		{
			sum += pq.poll() * k;
			if(k<K) 
			{
				k++;
			}
		}
		System.out.print(sum);
	}
}