//https://www.acmicpc.net/problem/20757
//1초 256MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int N, parent[];
		while(T-->0)
		{
			N = Integer.parseInt(br.readLine());
			parent = new int[N];
			
			for(int i=0; i<N; i++)
				parent[i] = i;
			
			int cnt = 0;
			
			for(int y=0; y<N; y++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int x=0; x<N; x++)
				{
					if(Integer.parseInt(st.nextToken()) == 0)
						continue;
					
					int p1 = find(y, parent);
					int p2 = find(x, parent);
					
					if(p1 == p2)
						continue;
					
					if(parent[p1] < parent[p2])
						parent[p2] = parent[p1];
					else
						parent[p1] = parent[p2];
					
					cnt += 1;
				}
			}
			
			sb.append(cnt).append('\n');
		}
		System.out.print(sb);
	}
	public static int find(int n, int parent[]) {
		if(parent[n] == n) return n;
		return parent[n] = find(parent[n], parent);
	}
}