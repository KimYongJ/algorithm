//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/8980
import java.util.Arrays;
import java.util.PriorityQueue;
class Node{
	int s, e, box;
	Node(int s,int e, int box){this.s=s;this.e=e;this.box=box;}
}
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->a.e-b.e);
		int N		= read();//마을수(2<=이천)
		int C		= read();//용량(1<=만)
		int M		= read();// 박스정보M(1<=만)
		int res		= 0;// 결과
		int capa[]	= new int[N]; // 각 노드별 최대담을 수 있는 용량 
		
		Arrays.fill(capa, C);

		while(M-->0) 
			pq.add(new Node(read(),read(),read()));
		
		while(!pq.isEmpty()) 
		{
			Node now	= pq.poll();
			int s		= now.s;
			int e		= now.e;
			int box		= now.box;
			
			for(int i=s; i<e; i++)
				box = Math.min(box, capa[i]);
			
			for(int i=s; i<e; i++) 
				capa[i] -= box;
			
			res += box;
		}
		System.out.print(res);
	}
}
/*
10 3000
5
8 10 2188
2 10 2840
1 10 2427
7 9 2843
9 10 2154
출5154
 * */
