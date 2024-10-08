//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/18436

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int[] arr, even;
	
	public static int initEven(int treeNode, int s, int e) {
		if(s == e)
			return even[treeNode] = arr[s]%2 == 0 ? 1 : 0;
		
		int mid			= (s + e) >> 1;
		int nextNode	= treeNode << 1;
		
		return even[treeNode] = initEven(nextNode, s, mid) + initEven(nextNode | 1, mid + 1, e);
	}
	
	public static void update(int treeNode, int s, int e, int originIdx) {
		if(originIdx < s || e < originIdx)
			return;
		if(s == e)
		{
			even[treeNode] = arr[s]%2 == 0 ? 1 : 0;
			return;
		}
		
		int mid			= (s + e) >> 1;
		int nextNode	= treeNode << 1;
		
		update(nextNode, s, mid, originIdx);
		update(nextNode | 1, mid + 1, e, originIdx);
		
		even[treeNode] = even[nextNode] + even[nextNode | 1];
	}
	
	public static int queryEven(int treeNode, int s, int e, int left, int right) {
		if(right < s || e < left)
			return 0;
		if(left <= s && e <= right)
			return even[treeNode];
		
		
		int mid			= (s + e) >> 1;
		int nextNode	= treeNode << 1;
		
		return queryEven(nextNode, s, mid, left, right) + queryEven(nextNode | 1, mid + 1, e, left, right);
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		int N	= Integer.parseInt(br.readLine());				// 1<=10만
		int H	= (int)Math.ceil(Math.log(N) / Math.log(2));	// 트리의 높이
		arr		= new int[N+1];
		even	= new int[1<<(H+1)];
		
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		initEven(1, 1, N);
		
		int Q = Integer.parseInt(br.readLine());
		while(Q-->0)
		{
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(cmd == 1)
			{
				arr[a] = b;
				update(1, 1, N, a);// 짝수를 늘림
			}
			else
			{
				int cnt = queryEven(1, 1, N, a, b);
				
				if(cmd == 3)
					cnt = (b-a+1) - cnt;
				
				sb.append(cnt).append('\n');
			}
		}
		System.out.print(sb);
	}
}