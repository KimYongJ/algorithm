//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/2336
//2초 192MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	
	static final int MAX = 1<<30;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N		= Integer.parseInt(br.readLine());
		Node []node = new Node[N + 1];
		int []tree	= new int[N * 4];
		
		node[0] = new Node(MAX,MAX,MAX);
		
		Arrays.fill(tree, MAX);
		
		for(int i=0; i<3; i++)		// 시험 3개 반복
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++)	// 등수
			{
				int rnk = Integer.parseInt(st.nextToken());
				
				if(node[rnk] == null)
					node[rnk] = new Node(0,0,0);
				
				if(i == 0)
				{
					node[rnk].rank1 = j;
				}
				else if(i == 1)
				{
					node[rnk].rank2 = j;
				}
				else
				{
					node[rnk].rank3 = j;
				}
			}
		}
		
		Arrays.sort(node);
		
		int ans = 0;
		
		for(Node now : node)
		{
			int min = query( 1, 1, N, 1, now.rank2, tree );
			
			if(min > now.rank3)
				++ans;
			
			update( 1, 1, N, now.rank2, now.rank3, tree );
		}
		
		System.out.print(ans);
	}
	public static void update(int treeNode, int s, int e, int targetIdx, int value, int[] tree) {
		if(e < targetIdx || targetIdx < s)
			return;
		
		if(s == e)
		{
			tree[treeNode] = value;
			return;
		}
		
		int mid = (s + e) >> 1;
		
		update(treeNode << 1, s, mid, targetIdx, value, tree);
		update(treeNode << 1 | 1, mid + 1, e, targetIdx, value, tree);
		
		tree[treeNode] = Math.min(tree[treeNode << 1], tree[treeNode << 1 | 1]);
	}
	public static int query(int treeNode, int s, int e, int left, int right, int[] tree) {
		
		if(e < left || right < s)
			return MAX;
		
		if(left<=s && e<= right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		int l = query(treeNode << 1, s, mid, left, right, tree);
		int r = query(treeNode << 1 | 1, mid + 1, e, left, right, tree);
		
		return Math.min(l, r);
	}
}
class Node implements Comparable<Node>{
	int rank1, rank2, rank3;
	Node(int r1, int r2, int r3){
		rank1 = r1;
		rank2 = r2;
		rank3 = r3;
	}
	@Override
	public int compareTo(Node o) {
		return rank1 - o.rank1;
	}
}