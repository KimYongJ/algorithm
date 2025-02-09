//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/5419
//1초 / 256MB

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;
class Node implements Comparable<Node>{
	int y, x;
	Node(int y, int x){
		this.y=y; this.x=x;
	}
	@Override
	public int compareTo(Node o) {
		if(o.x == x)
			return o.y - y;
		return x - o.x;
	}
}
class Main{

	static int N, ymax;
	static int tree[];
	static ArrayList<Node> pos;
	static TreeSet<Integer> set;
	static HashMap<Integer, Integer> map;
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		
		int T = read();
		
		while(T-->0)
		{
			N	= read();
			pos	= new ArrayList<>();
			set	= new TreeSet<>();
			map	= new HashMap<>();
			
			for(int i=0; i<N; i++)
			{
				int x = read();
				int y = read();
				pos.add(new Node(y, x));
				set.add(y);
			}
			// x기준 오름차순, y기준 내림차순
			Collections.sort(pos);
			
			ymax = 1;
			
			for(int s : set)
				map.put(s, ymax++);

			tree = new int[ymax << 2];
			
			long res = 0;
			
			for(Node now : pos)
			{
				int yIdx = map.get(now.y);
				
				res += query(1, 1, ymax, yIdx, ymax);
				
				update(1, 1, ymax, yIdx);
			}
			sb.append(res).append('\n');
		}
		System.out.print(sb);
	}
	public static void update(int treeNode, int s, int e, int idx) {
		if(e < idx || idx < s)
			return;
		
		tree[treeNode]++;
		
		if(s!=e)
		{
			int mid = (s + e) >> 1;
			update(treeNode << 1, s, mid, idx);
			update(treeNode << 1 | 1, mid + 1, e, idx);
		}
	}
	public static int query(int treeNode, int s, int e, int left, int right) {
		if(e < left || right < s)
			return 0;
		if(left <= s && e<= right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		return query(treeNode << 1, s, mid, left, right) + 
				query(treeNode << 1 | 1, mid + 1, e, left, right);
	}
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) {
        n = (n << 3) + (n << 1) + (c & 15);}
        return m ? ~n + 1 : n;
    }
}