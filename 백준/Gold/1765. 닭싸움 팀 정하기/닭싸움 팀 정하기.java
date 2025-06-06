//https://www.acmicpc.net/problem/1765
//2초 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

class Main{
	
	static int N, M;
	static int parent[];
	static List<Integer> adList[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());// 학생수 (2 ≤ 1000) 
		M = Integer.parseInt(br.readLine());// 인간관계 수(1 ≤ 5000)
		adList = new ArrayList[N + 1];
		parent = new int[N + 1];
		
		for(int i=0; i<=N; i++)
		{
			adList[i] = new ArrayList<>();
			parent[i] = i;
		}
		
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			char cmd = st.nextToken().charAt(0);
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			if(cmd == 'E')
			{
				adList[n1].add(n2);
				adList[n2].add(n1);
				continue;
			}
			
			int parent1 = find(n1);
			int parent2 = find(n2);
			if(parent1 == parent2)
				continue;
			
			if(parent[parent1] < parent[parent2])
				parent[parent2] = parent1;
			else
				parent[parent1] = parent2;
		}
		
		for(int now = 1; now<=N; now++)
		{
			for(int enemy : adList[now])
			{
				for(int friend : adList[enemy])
				{
					int parent1 = find(now);
					int parent2 = find(friend);
					if(parent1 == parent2)
						continue;
					
					if(parent[parent1] < parent[parent2])
						parent[parent2] = parent1;
					else
						parent[parent1] = parent2;
				}
			}
		}
		
		Set<Integer> set = new HashSet<>();
		
		for(int i=1; i<=N; i++)
			set.add(find(i));

		System.out.print(set.size());
	}
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
}