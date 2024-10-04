//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/6218

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int[] arr, minSeg, maxSeg;
	public static int initMin(int treeNode, int s, int e) {
		if(s == e)
			return minSeg[treeNode] = arr[s];
		
		int mid			= (s + e) >> 1;
		int nextNode	= treeNode << 1;
		int left		= initMin(nextNode, s, mid);
		int right		= initMin(nextNode | 1, mid + 1, e);
		
		return minSeg[treeNode] = Math.min(left, right);
	}
	public static int initMax(int treeNode, int s, int e) {
		if(s == e)
			return maxSeg[treeNode] = arr[s];
		
		int mid			= (s + e) >> 1;
		int nextNode	= treeNode << 1;
		int left		= initMax(nextNode, s, mid);
		int right 		= initMax(nextNode | 1, mid + 1, e);
		
		return maxSeg[treeNode] = Math.max(left, right);
	}
	public static int getMin(int treeNode, int s, int e, int left, int right) {
		if(right < s || e < left)
			return 1_000_000;
		if(left<=s && e<= right)
			return minSeg[treeNode];
		
		int mid			= (s + e) >> 1;
		int nextNode	= treeNode << 1;
		int l			= getMin(nextNode, s, mid, left, right);
		int r			= getMin(nextNode | 1, mid + 1, e, left, right);
		
		return Math.min(l, r);
	}
	public static int getMax(int treeNode, int s, int e, int left, int right) {
		if(right < s || e < left)
			return 0;
		if(left<=s && e<= right)
			return maxSeg[treeNode];
		
		int mid			= (s + e) >> 1;
		int nextNode	= treeNode << 1;
		int l			= getMax(nextNode, s, mid, left, right);
		int r			= getMax(nextNode | 1, mid + 1, e, left, right);
		
		return Math.max(l, r);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N	= Integer.parseInt(st.nextToken());	// 소의 수 (1<=오만)
		int Q	= Integer.parseInt(st.nextToken());	// 명령어 수 (1<=이십만)
		int H	= (int)Math.ceil(Math.log(N) / Math.log(2));
		
		arr		= new int[N + 1];
		minSeg	= new int[1 <<(H + 1)];
		maxSeg	= new int[1 <<(H + 1)];
		
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(br.readLine()); // 1<=백만
		
		initMin(1, 1, N);
		initMax(1, 1, N);
		
		while(Q-->0)
		{
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());

			sb.append(getMax(1, 1, N, l, r) - getMin(1, 1, N, l, r)).append('\n');
		}
		System.out.print(sb.toString());
	}
}