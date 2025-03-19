//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/27655
//1초 1024MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;
class Node {
	int left, right, floor;
	Node(int l, int r, int f){
		left=l;
		right=r;
		floor=f;
	}
}
class Main{
	
	static final int MAX = 1<<30;
	static int N, K, LEN;
	static int[] tree, lazy;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 발판의 개수
		K = Integer.parseInt(st.nextToken());	// 가장 높은 발판의 층
		
		if(N == 1)
		{
			System.out.print(0);
			return;
		}
		
		ArrayList<Node> list = new ArrayList<>();
		
		TreeSet<Integer> set = new TreeSet<>();
		for(int i=0; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			list.add(new Node(l,r,k));
			set.add(l);
			set.add(r);
		}
		HashMap<Integer, Integer> map = new HashMap<>();

		LEN = 1;
		
		for(int num : set)
			map.put(num, LEN++);
		
		LEN -= 1;
		
		Collections.sort(list, (a,b)-> a.floor - b.floor);
		
		tree = new int[LEN * 4];
		lazy = new int[LEN * 4];
		
		int ans = MAX;
		
		Arrays.fill(tree, ans);
		
		for(Node node : list)
		{
			if(node.floor == 1)
			{
				update(1, 1, LEN, map.get(node.left), map.get(node.right), 1);
			}
			else
			{
				int min = query(1, 1, LEN, map.get(node.left), map.get(node.right));
				
				update(1, 1, LEN, map.get(node.left), map.get(node.right), min + 1);
				
				if(node.floor == K)
				{
					ans = Math.min(ans, min);
				}
			}
		}
		
		System.out.print(ans == MAX ? -1 : ans);
	}
	public static int query(int treeNode, int s, int e, int left, int right) {
		
		propagate(treeNode, s, e);
		
		if(right < s || e < left)
			return MAX;
		if(left<=s && e<= right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		int nxt = treeNode << 1;
		
		int r1 = query(nxt, s, mid, left, right);
		int r2 = query(nxt | 1, mid + 1, e, left, right);
		
		return Math.min(r1, r2);
	}
	public static void update(int treeNode, int s, int e, int left, int right, int value) {
		
		propagate(treeNode, s, e);
		
		if(right < s || e < left)
			return;
		if(left<=s && e<= right)
		{
			lazy[treeNode] = value;
			propagate(treeNode, s, e);
			return;
		}
		int mid = (s + e) >> 1;
		int nxt = treeNode << 1;
		update(nxt, s, mid, left, right, value);
		update(nxt | 1, mid + 1, e, left, right, value);
		
		tree[treeNode] = Math.min(tree[nxt], tree[nxt | 1]);
	}
	public static void propagate(int treeNode, int s, int e) {
		if(lazy[treeNode] != 0)
		{
			tree[treeNode] = lazy[treeNode];
			if(s != e)
			{
				
				lazy[treeNode << 1]		= lazy[treeNode];
				lazy[treeNode << 1 | 1] = lazy[treeNode];
			}
			lazy[treeNode] = 0;
		}
	}
}

