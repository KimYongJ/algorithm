//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Node{
	int s,e;
	Node(int s, int e){this.s=s; this.e=e;}
}
class Main{
	public static void main(String[] args)throws Exception{
		PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->a.s-b.s);
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int idx = 0;
		int cnt = 0;
		for(int i=0; i<N; i++) 
		{
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			pq.add(new Node(s,e));
		}
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			int s = Math.max(now.s, idx);
			int e = now.e;
			int l = e-s;
			if(l > 0) {
				int c = l/L;
				if(l%L > 0) c++;
				cnt += c;
				idx = s + c*L;
			}
		}
		System.out.print(cnt);
	}
}