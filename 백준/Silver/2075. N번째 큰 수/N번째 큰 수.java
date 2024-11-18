//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/2075
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a);
		for(int y=0; y<N; y++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int x=0; x<N; x++)
				pq.add(Integer.parseInt(st.nextToken()));
		}
		
		while(--N>0)
			pq.poll();
		
		System.out.print(pq.poll());
	}
}