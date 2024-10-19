//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/24542

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
public class Main {
	public static int find(int node, int[] parent) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node], parent);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N			= Integer.parseInt(st.nextToken());	// 교육생 수 N(2<=이십만)
		int M			= Integer.parseInt(st.nextToken());	// 친분 관계의 수(1<=N-1)
		int parent[]	= new int[N+1];
		
		for(int i=1; i<=N; i++)
			parent[i] = i;
		
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(b < a)
			{
				int dummy = b;
				b = a;
				a = dummy;
			}
			
			int aParent = find(a, parent);
			int bParent = find(b, parent);
			
			parent[bParent] = aParent;
		}
		HashMap<Integer, Integer> hm = new HashMap<>();
		for(int i=1; i<=N; i++)
		{
			int p = find(i, parent);
			hm.put(p, hm.getOrDefault(p,0) + 1);
		}
		long res = 1;
		for(int value : hm.values())
			res = (res * value) % 1_000_000_007;
		
		System.out.print(res);
	}
}