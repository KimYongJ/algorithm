//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/4195
//3초 / 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
class Main{
	
	static HashMap<String, Integer> map = new HashMap<>();
	static int T, N, idx;
	static int[] parent, cnt;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			N		= Integer.parseInt(br.readLine());//1<=십만
			parent	= new int[N<<1];
			cnt		= new int[N<<1];
			idx		= 0;
			
			map.clear();
			
			for(int i=0; i<N; i++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				int parent1 = getParent( getNumber(st.nextToken()) );
				int parent2 = getParent( getNumber(st.nextToken()) );
				
				if(parent2 < parent1)
				{
					int tmp = parent2;
					parent2 = parent1;
					parent1 = tmp;
				}
				
				if(parent1 != parent2)
				{
					parent[parent2] = parent1;
					cnt[parent1] += cnt[parent2];
				}
				sb.append(cnt[parent1]).append('\n');
			}
		}
		System.out.print(sb);
	}
	public static int getNumber(String str) {
		if(map.containsKey(str))
		{
			return map.get(str);
		}
		map.put(str, idx);
		parent[idx] = idx;
		cnt[idx]	= 1;
		return idx++;
	}
	public static int getParent(int node) {
		if(parent[node] == node) return node;
		return parent[node] = getParent(parent[node]);
	}
}