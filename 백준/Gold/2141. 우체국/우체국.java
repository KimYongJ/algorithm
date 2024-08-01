// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Node{
	int idx,cnt;
	Node(int idx,int cnt){this.idx=idx; this.cnt=cnt;}
}
class Main{
	public static void main(String[] args)throws Exception{
		PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->a.idx-b.idx);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long sum = 1, tmpSum = 0;
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int cnt = Integer.parseInt(st.nextToken());
			sum += cnt;
			pq.add(new Node(idx, cnt));
		}
		for(int i=0; i<N; i++) {
			Node now = pq.poll();
			tmpSum += now.cnt;
			if(sum/2 <= tmpSum) {
				System.out.print(now.idx);
				return;
			}
		}
	}
}