// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Node{
	int s,e; Node(int s,int e){this.s=s;this.e=e;}
}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> rooms = new PriorityQueue<>();
		PriorityQueue<Node> list = new PriorityQueue<Node>((a,b)->a.s-b.s);
		int T = Integer.parseInt(br.readLine());
		while(T-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			st.nextToken();
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			list.add(new Node(s,e));
		}
		int res = 0;
		while(!list.isEmpty()) {
			Node now = list.poll();
			if(!rooms.isEmpty() && rooms.peek() <= now.s) 
			{
				rooms.poll();
			}

			rooms.add(now.e);
			
			if(res < rooms.size()) {
				res = rooms.size();
			}
		}
		System.out.print(res);
	}
}