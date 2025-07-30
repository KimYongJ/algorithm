//https://www.acmicpc.net/problem/14574
//1초 128MB
//3 // 참가하는 요리사의 수 (2<=1,000)
//1 2 // 요리사의 요리실력 + 인기도가 주어짐(0<=10^9)
//3 1
//5 3
//첫째줄에 가능한 시청률의 합의 최댓값 출력 둘째 줄부터 N번째 줄까지, 
//대결하는 두 요리사의 번호를 "패자 승자"의 형식으로 대결 순서대로 출력, 패자는 
//계속 남아 대결을 진행함
//답 :
//3
//2 1 // 패자, 승자 출력
//3 2
		
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	
	static int N;
	static long total;
	static int parent[];
	static int person[][];
	static List<Integer> adList[];
	static PriorityQueue<Node> pq;
	static StringBuilder order;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		parent = new int[N + 1]; 
		person = new int[N + 1][2];
		adList = new ArrayList[N + 1];
		pq = new PriorityQueue<>();
		order = new StringBuilder();
		
		for(int i=0; i<=N; i++)
		{
			parent[i] = i;
			adList[i] = new ArrayList<>();
		}
		
		for(int i=1; i<=N; i++)
		{
			st = new StringTokenizer(br.readLine());
			person[i][0] = Integer.parseInt(st.nextToken());
			person[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i<=N; i++)
		{
			for(int j=i+1; j<=N; j++)
			{
				int diff = Math.abs(person[i][0] - person[j][0]);
				if(diff == 0)
					continue;
				int sum = (person[i][1] + person[j][1]) / diff;
				pq.add(new Node(i,j, sum));
			}
		}
		
		int edgeCnt = 1;
		
		while(!pq.isEmpty() && edgeCnt != N) 
		{
			Node now = pq.poll();
			int p1 = find(now.a);
			int p2 = find(now.b);
			
			if(p1 == p2)
				continue;
			
			if(parent[p2] < parent[p1])
			{
				int tmp = p1;
				p1 = p2;
				p2 = tmp;
			}
			
			parent[p2] = p1;
			total += now.sum;
			edgeCnt += 1;
			adList[now.a].add(now.b);
			adList[now.b].add(now.a);
		}
		
		dfs(1, 0);
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(total).append('\n');
		sb.append(order.toString());
		System.out.print(sb);
	}
	static void dfs(int now, int parent)
	{
		
		for(int next : adList[now])
		{
			if(next == parent)
				continue;
			
			dfs(next, now);
		}
		
		if(parent > 0)
			order.append(parent).append(' ').append(now).append('\n');

	}
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
	static class Node implements Comparable<Node>{
		int a, b, sum;
		Node(int a, int b, int sum){
			this.a=a;
			this.b=b;
			this.sum=sum;
		}
		@Override
		public int compareTo(Node o) {
			return o.sum - sum;
		}
	}
}