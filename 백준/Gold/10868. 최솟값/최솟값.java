//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/10868
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static int[] arr, segTree;
	
	public static int init(int treeNode, int s, int e) {
		if(s == e)
			return segTree[treeNode] = arr[s];
		int mid = (s + e) >> 1;
		return segTree[treeNode] = Math.min(init(treeNode * 2, s, mid), init(treeNode * 2 + 1, mid + 1, e));
	}
	public static int getMin(int treeNode, int s, int e, int left, int right) {
		if(right < s || e < left)
			return 1<<30;
		if(left<=s && e<=right)
			return segTree[treeNode];
		
		int mid = (s + e) >> 1;
		return Math.min( getMin(treeNode*2, s, mid, left, right), getMin(treeNode*2 + 1, mid + 1, e, left, right) );
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N	= Integer.parseInt(st.nextToken());			// 정수들의 수(1<=십만)
		int M	= Integer.parseInt(st.nextToken());			// 범위수(1<=십만)
		int H	= (int)Math.ceil(Math.log(N) / Math.log(2));	// 세그먼트 트리의 높이
		arr		= new int[N+1];
		segTree = new int[1<<(H + 1)];
		
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(br.readLine());
		
		init(1, 1, N);
		
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(getMin(1, 1, N, a, b))
				.append('\n');
		}
		System.out.print(sb.toString());
	}
}