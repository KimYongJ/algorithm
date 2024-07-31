// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Node{
	int idx,num; Node(int idx, int num){this.idx=idx; this.num=num;}
}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N];
		PriorityQueue<Node> pq;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		int S = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) 
		{
			pq = new PriorityQueue<Node>((a,b)->b.num-a.num);
			for(int s=i; s<N; s++)
				pq.add(new Node(s, arr[s]));
			
			while(!pq.isEmpty()) {
				Node now = pq.poll();
				if(now.idx-i <= S) {
					S -= (now.idx-i);
					for(int j=now.idx; j>i; j--) {
						int tmp = arr[j];
						arr[j] = arr[j-1];
						arr[j-1] = tmp;
					}
					break;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int a : arr) sb.append(a).append(' ');
		System.out.print(sb.toString());
	}
}