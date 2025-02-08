//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/13537
//1초 / 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main{
	
	static int N, arr[];
	static ArrayList[] tree;
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		tree= new ArrayList[N << 2];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		init(1, 1, N);
		
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			sb.append(query(1, 1, N, l, r, v)).append('\n');
		}
		System.out.print(sb);
	}
	public static int query(int treeNode, int s, int e, int left, int right, int target) {
		if(e < left || right < s)
			return 0;
		if(left<=s && e<= right)
		{
			int idx = binarySearch(tree[treeNode], target);
			if(idx < 0)
				return 0;
			return tree[treeNode].size() - idx;
		}
		int mid = (s + e) >> 1;
		return query(treeNode << 1, s, mid, left, right, target) 
				+ query(treeNode << 1 | 1, mid + 1, e, left, right, target);
	}
	public static int binarySearch(ArrayList<Integer> list, int target) {
		int s = 0;
		int e = list.size() - 1;
		int r = -1;
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			if(list.get(mid) > target)
			{
				r = mid;
				e = mid - 1;
			}
			else
				s = mid + 1;
		}
		return r;
	}
	public static void init(int treeNode, int s, int e) {
		if(s == e)
		{
			tree[treeNode] = new ArrayList<>();
			tree[treeNode].add(arr[s]);
			return;
		}
		
		int mid = (s + e) >> 1;
		init(treeNode << 1, s, mid);
		init(treeNode << 1 | 1, mid + 1, e);
		tree[treeNode] = new ArrayList<>();
		merge(tree[treeNode], tree[treeNode << 1], tree[treeNode << 1 | 1]);
	}
	public static void merge(ArrayList<Integer> list, ArrayList<Integer>left, ArrayList<Integer> right) {
		int l = 0;
		int r = 0;
		while(l < left.size() && r < right.size())
		{
			if(left.get(l) <= right.get(r))
				list.add(left.get(l++));
			else
				list.add(right.get(r++));
		}
		while(l<left.size())list.add(left.get(l++));
		while(r<right.size())list.add(right.get(r++));
	}
}