//https://www.acmicpc.net/problem/23040
//2초 1024MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static int N;
	static char color[];
	static int cnt[];
	static int parent[];
	static List<Node> edge;
	static List<Integer> adList[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		parent = new int[N + 1];
		cnt = new int[N + 1];
		color = new char[N + 1];
		edge = new ArrayList<>();
		adList = new ArrayList[N + 1];
		
		for(int i=0; i<=N; i++)
		{
			cnt[i] = 1;
			parent[i] = i;
			adList[i] = new ArrayList<>();
		}
		
		for(int i=1; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adList[a].add(b);
			adList[b].add(a);
			edge.add(new Node(a,b));
		}
		
		String str = br.readLine();
		for(int i=0; i<N; i++)
			color[i + 1] = str.charAt(i);
		
		for(Node now : edge)
		{
			if(color[now.a] == color[now.b] && color[now.a]== 'R')
			{
				int p1 = find(now.a);
				int p2 = find(now.b);
				if(p1 == p2)
					continue;
				
				if(parent[p1] < parent[p2])
				{
					parent[p2] = p1;
					cnt[p1] += cnt[p2];
				}
				else
				{
					parent[p1] = p2;
					cnt[p2] += cnt[p1];
				}
			}
		}
		
		long sum = 0;
		
		for(int i=1; i<=N; i++)
			if(color[i] == 'B')
				for(int next : adList[i])
					if(color[next] == 'R')
						sum += cnt[find(next)];

		System.out.print(sum);
	}
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
	static class Node{
		int a, b;
		Node(int a, int b){
			this.a=a;
			this.b=b;
		}
	}
}