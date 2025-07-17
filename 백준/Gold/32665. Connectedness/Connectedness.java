//https://www.acmicpc.net/problem/32665
//1초 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int cnt[] = new int[N + 1];
		int parent[] = new int[N + 1];
		
		for(int i=0; i<=N; i++)
		{
			cnt[i] = 1;
			parent[i] = i;
		}
		
		int edgeCnt = 0;
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int p1 = find(Integer.parseInt(st.nextToken()), parent);
			int p2 = find(Integer.parseInt(st.nextToken()), parent);
			
			edgeCnt++;
			
			if(p1 == p2)
				continue;
			
			if(parent[p1] < parent[p2]) {
				parent[p2] = p1;
				cnt[p1] += cnt[p2];
			}else {
				parent[p1] = p2;
				cnt[p2] += cnt[p1];
			}
			
			if(cnt[1] == N) {
				System.out.print(edgeCnt);
				return;
			}
		}
		
		System.out.print(cnt[1] == N ? edgeCnt : -1);
	}
	static int find(int node, int parent[]) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node], parent);
	}
}