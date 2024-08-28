// https://github.com/kimyongj/algorithm
// https://www.acmicpc.net/problem/4716
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Node{
	int count, a, b, diff;
	Node(int cnt, int a, int b, int diff){
		this.count=cnt;	this.a=a;this.b=b;this.diff=diff;
	}
}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		while(true) 
		{
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 팀수
			int A = Integer.parseInt(st.nextToken()); // A방 풍선 수
			int B = Integer.parseInt(st.nextToken()); // B방 풍선 수
			
			if(N==0 && A==0 && B==0)
				break;
			
			PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->b.diff-a.diff);
			int result = 0; // 총 이동거리
			for(int i=0; i<N; i++) 
			{
				st = new StringTokenizer(br.readLine());
				int k = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				pq.add(new Node(k,a, b, Math.abs(a-b)));
			}
			
			while(!pq.isEmpty()) 
			{
				Node now = pq.poll();
				if(now.a > now.b) // b로가는게 더 이득일 때 
				{
					int balloon = Math.min(B, now.count); // 최소로 없앨 수 있는 풍선 수
					B -= balloon;
					now.count -= balloon;
					result += balloon * now.b;
					if(now.count > 0) {
						result += now.count * now.a;
						A -= now.count;
					}
				}
				else // a로가는게 더 이득일 때
				{
					int balloon = Math.min(A, now.count); // 최소로 없앨 수 있는 풍선 수
					A -= balloon;
					now.count -= balloon;
					result += balloon * now.a;
					if(now.count > 0) {
						result += now.count * now.b;
						B -= now.count;
					}
				}
			}
			sb.append(result).append('\n');
		}
		System.out.print(sb.toString());
	}
}