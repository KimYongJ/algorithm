//https://www.acmicpc.net/problem/17250
//5초 512MB
//5 4//노드수(2<=100,000), 간선 수(1<=100,000)
//3// N개 줄에 각 은하 내에 존재하는 행성 수가 주어짐
//9
//10
//11
//15
//1 2//간선 개수만큼 연결된 두 노드가 주어짐
//2 3
//4 5
//4 3
//답
//12
//22
//26
//48


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{

	static int N, M;
	static int cnt[];
	static int parent[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());//노드수(2<=100,000)
		M = Integer.parseInt(st.nextToken());//간선 수(1<=100,000)
		cnt = new int[N + 1];
		parent = new int[N + 1];
		
		for(int i=1; i<=N; i++)
		{
			cnt[i] = Integer.parseInt(br.readLine());
			parent[i] = i;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int p1 = find(Integer.parseInt(st.nextToken()));
			int p2 = find(Integer.parseInt(st.nextToken()));
			if(p1 == p2) {
				sb.append(cnt[p1]).append('\n');
			}
			else if(parent[p1] < parent[p2]) {
				parent[p2] = p1;
				cnt[p1] += cnt[p2];
				sb.append(cnt[p1]).append('\n');
			}
			else {
				parent[p1] = p2;
				cnt[p2] += cnt[p1];
				sb.append(cnt[p2]).append('\n');
			}
		}
		System.out.print(sb);
	}
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
}