// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
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
		ArrayList<Node> list = new ArrayList<>();
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i=0; i<N; i++) 
		{
			st		= new StringTokenizer(br.readLine());
			int s	= Integer.parseInt(st.nextToken());
			int e	= Integer.parseInt(st.nextToken());
			list.add(new Node(s,e));
		}
		Collections.sort(list, (a,b)->{
			if(a.s==b.s)return a.e-b.e;
			return a.s-b.s;
		});
		int cnt = 0;
		for(int i=0; i<N; i++) 
		{
			Node now = list.get(i);
			if(!pq.isEmpty() && pq.peek() <= now.s) {
				pq.poll();
			}else {
				cnt++;
			}
			pq.add(now.e);
		}
		System.out.print(cnt);
	}
}