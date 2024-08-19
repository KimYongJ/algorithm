// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Node{
	int w, c; Node(int w,int c){this.w=w;this.c=c;}
}
class Main{
	public static void main(String[] args)throws Exception{
		PriorityQueue<Integer> Kpq	= new PriorityQueue<>();
		PriorityQueue<Node> list	= new PriorityQueue<Node>((a,b)->a.w-b.w);
		PriorityQueue<Integer> store= new PriorityQueue<Integer>((a,b)->b-a);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) 
		{
			st = new StringTokenizer(br.readLine());
			list.add(new Node(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
		}
		
		for(int i=0; i<K; i++)
			Kpq.add(Integer.parseInt(br.readLine()));
		
		long res = 0;
		
		while(!Kpq.isEmpty())
		{
			int weight = Kpq.poll();
			
			while(!list.isEmpty() && list.peek().w <= weight)
				store.add(list.poll().c);
			
			if(!store.isEmpty())
				res += store.poll();
		}
		System.out.print(res);
	}
}