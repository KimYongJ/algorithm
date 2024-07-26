// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Node{int s,e;Node(int s,int e){this.s=s; this.e=e;}}
class Main{
	public static void main(String[] args)throws Exception{
		PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->a.e-b.e);
		BufferedReader br	= new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb	= new StringBuilder();
		StringTokenizer st;
		int P = Integer.parseInt(br.readLine());
		int T = 0, cnt, arr[];
		while(P != 0) 
		{
			pq.clear();
			cnt = 0;
			arr = new int[24];
			for(int i=0; i<P; i++) 
			{
				st = new StringTokenizer(br.readLine());
				pq.add(new Node(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
			}
			while(!pq.isEmpty()) {
				Node now = pq.poll();
				for(int s = now.s; s<now.e; s++) {
					if(arr[s] < 2) {
						++arr[s];
						++cnt;
						break;
					}
				}
			}
			
			sb.append(String.format("On day %d Emma can attend as many as %d parties.\n", ++T, cnt));
			P = Integer.parseInt(br.readLine());
		}
		System.out.print(sb.toString());
	}
}