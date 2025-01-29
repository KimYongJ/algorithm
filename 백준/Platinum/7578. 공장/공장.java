//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/7578
//1초 / 256MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N;
	static int[] arr, tree, counting;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N			= Integer.parseInt(br.readLine());	// 1<=오십만
		tree		= new int[N<<2];
		counting	= new int[1_000_001];				// 입력수 0<=백만
		arr			= new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			counting[Integer.parseInt(st.nextToken())] = i;
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=1; i<=N; i++)
			arr[i] = counting[Integer.parseInt(st.nextToken())];
		
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
}