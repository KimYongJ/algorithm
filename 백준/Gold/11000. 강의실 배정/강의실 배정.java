// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Node{
	int s,e;
	Node(int s,int e){this.s=s;this.e=e;}
}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		Node[] list = new Node[N];
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i=0; i<N; i++) 
		{
			st		= new StringTokenizer(br.readLine());
			int s	= Integer.parseInt(st.nextToken());
			int e	= Integer.parseInt(st.nextToken());
			list[i] = new Node(s,e);
		}
		
		Arrays.sort(list,(a,b)->a.s-b.s);

		pq.add(list[0].e);
		
		int cnt = 1;
		
		for(int i=1; i<N; i++) 
		{
			if(pq.peek() <= list[i].s) pq.poll();
			else cnt++;
			pq.add(list[i].e);
		}
		System.out.print(cnt);
	}
}