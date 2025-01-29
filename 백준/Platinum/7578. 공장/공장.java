//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/7578
//1초 / 256MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
class Node implements Comparable<Node>{
	int idx, value;
	Node(int i, int v){
		idx=i; value=v;
	}
	@Override
	public int compareTo(Node o) {
		return value - o.value;
	}
}
class Main{
	
	static int N, arr[], tree[];
	static ArrayList<Node> list;
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N	= Integer.parseInt(br.readLine());	// 1<=오십만
		tree= new int[N<<2];
		list= new ArrayList<>();
		arr	= new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			list.add(new Node(i, Integer.parseInt(st.nextToken())));
		
		Collections.sort(list);
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=1; i<=N; i++)
			arr[i] = binarySearch(Integer.parseInt(st.nextToken()));
		
		long res = 0;
		for(int i=N; i>0; i--)
		{
			res += query(1, 1, N, 1, arr[i] - 1);
			update(1, 1, N, arr[i]);
		}
		System.out.print(res);
	}
	public static void update(int treeNode, int s, int e, int idx) {
		if(idx<s || e<idx)
			return;
		
		tree[treeNode] += 1;
		
		if(s != e)
		{
			int nextNode = treeNode << 1;
			int mid = (s + e) >> 1;
			update(nextNode, s, mid, idx);
			update(nextNode | 1, mid + 1, e, idx);
		}
	}
	public static int query(int treeNode, int s, int e, int left, int right) {
		if(left <= s && e<= right)
			return tree[treeNode];
		if(e < left || right < s)
			return 0;
		
		int nextNode = treeNode << 1;
		int mid = (s + e) >> 1;
		return query(nextNode, s, mid, left, right) +
				query(nextNode | 1, mid + 1, e, left, right);
	}
	public static int binarySearch(int target) {
		int s = 0;
		int e = N-1;
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			int value = list.get(mid).value;
			if(value == target)
				return list.get(mid).idx;
			if(value < target)
				s = mid + 1;
			else
				e = mid - 1;
		}
		return 0;
	}
}