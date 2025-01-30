//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/9426
//1초 / 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static final int len = 65536;
	static int N, K;
	static int[] arr, tree;
	static long result;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());	// 1<=이십오만
		K		= Integer.parseInt(st.nextToken());	// 1<=오천
		tree	= new int[len << 2];
		arr		= new int[N];
		
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(br.readLine());	// 0<=65535
		
		for(int i=0; i<K; i++)
			update(1, 0, len, arr[i], 1);
		
		int k = (K+1) >> 1;
		
		result += query(1, 0, len, k);
		
		for(int i=K,j=0; i<N; i++,j++)
		{
			update(1, 0, len, arr[j], -1);
			update(1, 0, len, arr[i], 1);
			result += query(1, 0, len, k);
		}
		
		System.out.print(result);
	}
	public static int query(int treeNode, int s, int e, int target) {
		if(s == e)
			return s;
		
		int nextNode	= treeNode << 1;
		int mid			= (s + e) >> 1;

		if(tree[nextNode] >= target)
			return query(nextNode, s, mid, target);
		else
			return query(nextNode | 1, mid + 1, e, target - tree[nextNode]);
	}
	public static void update(int treeNode, int s, int e, int idx, int value) {
		if(e<idx || idx < s)
			return;
		
		tree[treeNode] += value;
		
		if(s != e)
		{
			int nextNode	= treeNode << 1;
			int mid			= (s + e) >> 1;
			update(nextNode, s, mid, idx, value);
			update(nextNode | 1, mid + 1, e, idx, value);
		}
	}
}