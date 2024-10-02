//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/2357
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static int[] arr, maxSeg, minSeg;
	public static int maxInit(int treeNode, int s, int e) {
		if(s == e)
			return maxSeg[treeNode] = arr[s];
		int mid = (s + e) >> 1;
		return maxSeg[treeNode] = Math.max(maxInit(treeNode * 2, s, mid), maxInit(treeNode * 2 + 1, mid + 1, e));
	}
	public static int minInit(int treeNode, int s, int e) {
		if(s == e)
			return minSeg[treeNode] = arr[s];
		int mid = (s + e) >> 1;
		return minSeg[treeNode] = Math.min(minInit(treeNode * 2, s, mid), minInit(treeNode * 2 + 1, mid + 1, e));
	}
	public static int getMax(int treeNode, int s, int e, int left, int right) {
		if(right < s || e < left) 
			return 0;
		if(left<=s && e<=right)
			return maxSeg[treeNode];
		
		int mid = (s + e) >> 1;
		return Math.max(getMax(treeNode * 2, s, mid, left, right), getMax(treeNode * 2 + 1, mid + 1, e, left, right));
	}
	public static int getMin(int treeNode, int s, int e, int left, int right) {
		if(right < s || e < left) 
			return Integer.MAX_VALUE;
		if(left<=s && e<=right)
			return minSeg[treeNode];
		
		int mid = (s + e) >> 1;
		return Math.min(getMin(treeNode * 2, s, mid, left, right), getMin(treeNode * 2 + 1, mid + 1, e, left, right));
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());	// 정수의 개수(1<=십만)
		int M = Integer.parseInt(st.nextToken());	// 명령어 순서
		int H = (int)Math.ceil(Math.log(N) / Math.log(2));
		arr		= new int[N+1];
		maxSeg	= new int[1<<(H + 1)];
		minSeg	= new int[1<<(H + 1)];
		
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(br.readLine());
		
		maxInit(1, 1, N);
		minInit(1, 1, N);
		
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			sb.append(getMin(1, 1, N, s, e)).append(' ')
				.append(getMax(1, 1, N, s, e)).append('\n');
		}
		
		System.out.print(sb.toString());
	}
}