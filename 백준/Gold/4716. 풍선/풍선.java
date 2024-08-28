// https://github.com/kimyongj/algorithm
// https://www.acmicpc.net/problem/4716
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Node{
	int count, aDist, bDist, diff;
	Node(int cnt, int a, int b, int diff){
		this.count=cnt;	this.aDist=a;this.bDist=b;this.diff=diff;
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
				Node t = pq.poll();
				if(t.aDist < t.bDist) {
					if(A >= t.count) {
						result += t.count * t.aDist;
						A -= t.count;
					}else {
						result += A * t.aDist;
						t.count -= A;
						A = 0;
						
						result += t.count * t.bDist;
						B -= t.count;
					}
				}else {
					if(B >= t.count) {
						result += t.count * t.bDist;
						B -= t.count;
					}else {
						result += B * t.bDist;
						t.count -= B;
						B = 0;
						
						result += t.count * t.aDist;
						A -= t.count;
					}
				}
//				if(now.aDist > now.bDist) // b로가는게 더 이득일 때 
//				{
//					if(B >= now.remain) {
//						B -= now.remain;
//						result += now.remain * now.b;
//					}else {
//						result += B * now.b;
//						now.remain -= B;
//						B = 0;
//						A -= now.remain;
//						result += now.remain * now.a;
//					}
//				}
//				else // a로가는게 더 이득일 때
//				{
//					if(A >= now.remain) {
//						A -= now.remain;
//						result += now.remain * now.a;
//					}else {
//						result += A * now.a;
//						now.remain -= A;
//						A = 0;
//						B -= now.remain;
//						result += now.remain * now.b;
//					}
//				}
			}
			sb.append(result).append('\n');
		}
		System.out.print(sb.toString());
	}
}