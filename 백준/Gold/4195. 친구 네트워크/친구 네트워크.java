//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/4195
//3초 / 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;
class Main{
	
	static HashSet<String> set = new HashSet<>();
	static HashMap<String, Integer> map = new HashMap<>();
	static String[][] rel;
	static int[] parent, cnt;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			int N = Integer.parseInt(br.readLine());//1<=십만
			rel = new String[N][2];
			set.clear();
			map.clear();
			for(int i=0; i<N; i++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				rel[i][0] = st.nextToken();
				rel[i][1] = st.nextToken();
				set.add(rel[i][0]);
				set.add(rel[i][1]);
			}
			
			int idx = 0;
			
			for(String s : set)
				map.put(s, idx++);
			
			parent	= new int[idx];
			cnt		= new int[idx];
			
			for(int i=0; i<idx; i++)
			{
				parent[i]	= i;
				cnt[i]		= 1;
			}
			
			for(int i=0; i<N; i++)
			{
				int parent1 = getParent(map.get(rel[i][0]));
				int parent2 = getParent(map.get(rel[i][1]));
				
				if(parent2 < parent1)
				{
					int tmp = parent1;
					parent1 = parent2;
					parent2 = tmp;
				}

				parent[parent2] = parent1;
				
				if(parent1 != parent2)
					cnt[parent1] += cnt[parent2];
				
				sb.append(cnt[parent1]).append('\n');
			}
			
		}
		System.out.print(sb);
	}
	public static int getParent(int node) {
		if(parent[node] == node) return node;
		return parent[node] = getParent(parent[node]);
	}
}
