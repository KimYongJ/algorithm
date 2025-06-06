//https://www.acmicpc.net/problem/24391
//2초 256MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int parent[] = new int[N + 1];
		
		for(int i=1; i<=N; i++)
			parent[i] = i;
		
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int parent1 = find(Integer.parseInt(st.nextToken()), parent);
			int parent2 = find(Integer.parseInt(st.nextToken()), parent);
			if(parent1 == parent2)
				continue;
			
			if(parent[parent1] < parent[parent2])
				parent[parent2] = parent1;
			else
				parent[parent1] = parent2;
		}
		st = new StringTokenizer(br.readLine());
		int prevNode = 0;
		int result = -1;
		while(N-->0)
		{
			int parentNode = find(Integer.parseInt(st.nextToken()), parent);
			
			if(prevNode != parentNode)
				++result;
			
			prevNode = parentNode;
		}
		System.out.print(result);
	}
	static int find(int node, int parent[]) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node], parent);
	}
}