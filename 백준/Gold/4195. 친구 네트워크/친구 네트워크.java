//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/4195
//3초 / 256MB
import java.util.HashMap;
class Main{
	
	static HashMap<String, Integer> map = new HashMap<>();
	static int T, N, idx;
	static int[] parent, cnt;
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		T = read();
		while(T-->0)
		{
			N		= read();//1<=십만
			parent	= new int[N<<1];
			cnt		= new int[N<<1];
			idx		= 0;
			
			map.clear();
			
			for(int i=0; i<N; i++)
			{
				int parent1 = getParent( getNumber(readString()) );
				int parent2 = getParent( getNumber(readString()) );
				
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
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	} 
	static String readString() throws Exception{
		StringBuilder sb = new StringBuilder();
		int c = System.in.read();
		while(c <= 32) {c = System.in.read();}
		while(c > 32) {
			sb.append((char)c);
			c = System.in.read();
		}
		return sb.toString();
	}
}
