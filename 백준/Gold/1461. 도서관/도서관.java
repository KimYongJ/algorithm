// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Main{

	public static int getDist(PriorityQueue<Integer> pq, int M) {
		int sum = 0;
		int i = 0;
		while(!pq.isEmpty()) {
			int n = pq.poll();
			if(i % M == 0)
				sum += n<<1;
			i++;
		}
		return sum;
	}

	public static void main(String[] args)throws Exception{
		BufferedReader br		= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st		= new StringTokenizer(br.readLine());
		PriorityQueue<Integer> pos = new PriorityQueue<Integer>((a,b)->b-a);
		PriorityQueue<Integer> neg = new PriorityQueue<Integer>((a,b)->b-a);
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int max = 0;
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) 
		{
			int n = Integer.parseInt(st.nextToken());
			if(n < 0) 
				neg.add(n = Math.abs(n));
			else
				pos.add(n);
			if(max < n)
				max = n;
		}
		
		System.out.print(getDist(pos,M) + getDist(neg,M) - max);
	}
}