//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/8980
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Node{
	int s, e, box;
	Node(int s,int e, int box){this.s=s;this.e=e;this.box=box;}
}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());//마을수(2<=이천)
		int C = Integer.parseInt(st.nextToken());//용량(1<=만)
		int M = Integer.parseInt(br.readLine());// 박스정보M(1<=만)
		PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->a.e!=b.e ? a.e-b.e : a.s-b.s);
		while(M-->0) 
		{
			st = new StringTokenizer(br.readLine());
			pq.add(new Node(
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())));
		}
		
		int res = 0; // 결과
		int capa[] = new int[N]; // 각 노드별 최대담을 수 있는 용량 
		Arrays.fill(capa, C);
		while(!pq.isEmpty()) 
		{
			Node now = pq.poll();
			int s = now.s;
			int e = now.e;
			int box = now.box;
			
			int min = Integer.MAX_VALUE;
			for(int i=s; i<e; i++)
				min = Math.min(min, capa[i]);
			
			res += min = Math.min(box, min);
			
			for(int i=s; i<e; i++) 
				capa[i] -= min;
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