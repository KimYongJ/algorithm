//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/15459
//2초 / 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int[] arr, max, tree;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N	= Integer.parseInt(st.nextToken());	// 1<=십만
		long M	= Long.parseLong(st.nextToken());	// 1<=100경
		arr		= new int[N+1];
		max		= new int[N+1];
		tree	= new int[N<<2];
		
		for(int i=1; i<=N; i++)
		{
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());	// 1<=10억
			max[i] = Integer.parseInt(st.nextToken());	// 1<=10억
		}
		
		init(1, 1, N);
		
		long sum = 0;
		int result = Integer.MAX_VALUE;	// 식사의 최소 매운맛
		int s = 1;
		int e = 1;
		while(e<=N)
		{
			sum += arr[e];

			while(M <= sum)
			{
				int max = query(1, 1, N, s, e);
				result = Math.min(result, max);
				sum -= arr[s++];
			}

			++e;
		}
		
		System.out.print(result);
	}
	public static int init(int treeNode, int s, int e) {
		if(s == e)
			return tree[treeNode] = max[s];
		
		int nextNode = treeNode << 1;
		int mid = (s + e) >> 1;
		int num1 = init(nextNode, s, mid);
		int num2 = init(nextNode | 1, mid + 1, e);
		
		return tree[treeNode] = Math.max(num1, num2);
	}
	public static int query(int treeNode, int s, int e, int left, int right) {
		if(e < left || right < s)
			return 0;
		if(left<=s && e<=right)
			return tree[treeNode];
		int nextNode = treeNode << 1;
		int mid = (s + e) >> 1;
		int num1 = query(nextNode, s, mid, left, right);
		int num2 = query(nextNode | 1, mid + 1, e, left, right);
		return Math.max(num1, num2);
	}
}