// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Node{
	long s,e; Node(long s,long e){this.s=s; this.e=e;}
}
class Main{
	public static void main(String[] args)throws Exception{
		PriorityQueue<Node> list = new PriorityQueue<Node>((a,b)->Long.compare(a.s,b.s));
		PriorityQueue<Long> rooms= new PriorityQueue<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N	= Integer.parseInt(br.readLine());
		int cnt = 0;
		for(int i=0; i<N; i++) 
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			long s = Long.parseLong(st.nextToken());
			long e = Long.parseLong(st.nextToken());
			list.add(new Node(s,e));
		}
		while(!list.isEmpty())
		{
			Node now = list.poll();
			
			if(!rooms.isEmpty() && rooms.peek() <= now.s)
				rooms.poll();
			else 
				cnt++;
			
			rooms.add(now.e);
			
		}
		System.out.print(cnt);
	}
}