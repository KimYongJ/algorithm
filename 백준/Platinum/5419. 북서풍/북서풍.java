//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/5419
//1초 / 256MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;
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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-->0)
		{
			N	= Integer.parseInt(br.readLine());
			pos	= new ArrayList<>();
			set	= new TreeSet<>();
			map	= new HashMap<>();
			
			for(int i=0; i<N; i++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
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
	
}