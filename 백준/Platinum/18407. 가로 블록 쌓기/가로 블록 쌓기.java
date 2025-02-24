//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/18407
//1초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Main{
	
	static int N;
	static int[] E, S, tree, lazy;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 1<=십만
		E = new int[N + 1];
		S = new int[N + 1];
		
		TreeSet<Integer> set			= new TreeSet<>();
		HashMap<Integer, Integer> hm	= new HashMap<>();
		
		for(int i=1; i<=N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			E[i] = Integer.parseInt(st.nextToken());//1<=십억
			S[i] = Integer.parseInt(st.nextToken());//1<=십억
			E[i] += S[i] - 1; // 칸을기준으로 하기 때문에 -1처리
			set.add(E[i]);
			set.add(S[i]);
		}
		
		int idx = 1;
		for(int s : set)hm.put(s, idx++);
		
		--idx;
		
		tree = new int[idx * 4];
		lazy = new int[idx * 4];
		
		for(int i=1; i<=N; i++)
		{
			int s = hm.get(S[i]);
			int e = hm.get(E[i]);
			int v = query(1, 1, idx, s, e)+1;
			update(1, 1, idx, s, e, v);
		}
		
		System.out.print(tree[1]);
	}
	public static int query(int treeNode, int s, int e, int left, int right) {
		propagate(treeNode, s, e);
		if(e < left || right < s)
			return 0;
		if(left<=s && e<=right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		return Math.max(query(treeNode << 1, s, mid, left, right), 
				query(treeNode << 1 | 1, mid + 1, e, left, right)
			);
	}
	public static void update(int treeNode, int s, int e, int left, int right, int value) {
		propagate(treeNode, s, e);
		if(e < left || right < s)
			return;
		if(left<=s && e<=right)
		{
			lazy[treeNode] = value;
			propagate(treeNode, s, e);
			return;
		}
		int mid = (s + e) >> 1;
		update(treeNode << 1, s, mid, left, right, value);
		update(treeNode << 1 | 1, mid + 1, e, left, right, value);
		tree[treeNode] = Math.max(tree[treeNode << 1], tree[treeNode <<1 | 1]);
	}
	public static void propagate(int treeNode, int s,int e) {
		if(lazy[treeNode] != 0)
		{
			tree[treeNode] = Math.max(tree[treeNode],lazy[treeNode]);
			if(s != e)
			{
				lazy[treeNode << 1]		= Math.max(lazy[treeNode << 1], lazy[treeNode]);
				lazy[treeNode << 1 | 1] = Math.max(lazy[treeNode << 1 | 1], lazy[treeNode]);
			}
			lazy[treeNode] = 0;
		}
	}
}
