//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/12886
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
class Node{
	int a,b,c;Node(int a,int b,int c){this.a=a;this.b=b;this.c=c;}
}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		boolean AB[][] = new boolean[1500][1500];
		boolean BC[][] = new boolean[1500][1500];
		boolean CA[][] = new boolean[1500][1500];
		
		ArrayDeque<Node> q = new ArrayDeque<>();
		q.add(new Node(A,B,C));
		AB[A][B] = BC[B][C] = CA[C][A] = true;
		while(!q.isEmpty())
		{
			Node now = q.poll();
			if(now.a == now.b && now.b == now.c) {
				System.out.print(1);
				return;
			}
			int nextA,nextB,nextC;
			if(now.a < now.b) {
				nextA = now.a<<1;
				nextB = now.b - now.a;
				if(!(AB[nextA][nextB] && BC[nextB][now.c] && CA[now.c][nextA])) {
					AB[nextA][nextB] = BC[nextB][now.c] = CA[now.c][nextA] = true;
					q.add(new Node(nextA, nextB, now.c));
				}
			}
			else if(now.b < now.a) {
				nextA = now.a - now.b;
				nextB = now.b <<1;
				if(!(AB[nextA][nextB] && BC[nextB][now.c] && CA[now.c][nextA])) {
					AB[nextA][nextB] = BC[nextB][now.c] = CA[now.c][nextA] = true;
					q.add(new Node(nextA, nextB, now.c));
				}
			}
			if(now.b < now.c) {
				nextC = now.c - now.b;
				nextB = now.b <<1;
				if(!(AB[now.a][nextB] && BC[nextB][nextC] && CA[nextC][now.a])) {
					AB[now.a][nextB] = BC[nextB][nextC] = CA[nextC][now.a] = true;
					q.add(new Node(now.a, nextB, nextC));
				}
			}
			else if(now.c < now.b) {
				nextC = now.c << 1;
				nextB = now.b - now.c;
				if(!(AB[now.a][nextB] && BC[nextB][nextC] && CA[nextC][now.a])) {
					AB[now.a][nextB] = BC[nextB][nextC] = CA[nextC][now.a] = true;
					q.add(new Node(now.a, nextB, nextC));
				}
			}
			if(now.a < now.c) {
				nextA = now.a<<1;
				nextC = now.c - now.a;
				if(!(AB[nextA][now.b] && BC[now.b][nextC] && CA[nextC][nextA])) {
					AB[nextA][now.b] = BC[now.b][nextC] = CA[nextC][nextA] = true;
					q.add(new Node(nextA, now.b, nextC));
				}
			}
			else if(now.c < now.a) {
				nextA = now.a - now.c;
				nextC = now.c<<1;
				if(!(AB[nextA][now.b] && BC[now.b][nextC] && CA[nextC][nextA])) {
					AB[nextA][now.b] = BC[now.b][nextC] = CA[nextC][nextA] = true;
					q.add(new Node(nextA, now.b, nextC));
				}
			}
			
		}
		System.out.print(0);
	}
}
