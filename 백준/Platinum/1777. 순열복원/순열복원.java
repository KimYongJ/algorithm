//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1777
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static int N, H, idx;
	static int[] arr, tree, res;
	public static int init(int treeNode, int s, int e) {
		if(s == e)
			return tree[treeNode] = 1;
		
		int mid = (s + e) >> 1;
		return tree[treeNode] = init(treeNode*2, s, mid) + init(treeNode*2 + 1, mid + 1, e);
	}
	public static void update(int treeNode, int s, int e, int originIdx, int diff) {
		if(originIdx < s || e < originIdx)
			return;
		if(s == e)
		{
			tree[treeNode] += diff;
			return;
		}
		
		tree[treeNode] += diff;
		
		int mid = (s + e) >> 1;

		update(treeNode * 2, s, mid, originIdx, diff);
		update(treeNode * 2 + 1, mid + 1, e, originIdx, diff);
	}
	public static void query(int treeNode, int s, int e, int cnt) {
		if(s == e) {
			update(1, 1, N, s, -1);
			res[N - s + 1] = idx--;
			return;
		}
		
		int mid = (s + e) >> 1;
		
		if(cnt < tree[treeNode * 2])
			query(treeNode * 2, s, mid, cnt);
		else
			query(treeNode * 2 + 1, mid + 1, e, cnt - tree[treeNode * 2]);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N	= Integer.parseInt(br.readLine());	// 순열 크기 (1<=십만)
		H	= (int)Math.ceil(Math.log(N+1) / Math.log(2));
		idx = N;
		arr = new int[N + 1];
		res = new int[N + 1];
		tree= new int[1<<(H+1)];
		
		init(1, 1, N);
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		for(int i=N; i>0; i--)
			query(1, 1, N, arr[i]);// arr[i]의 숫자만큼 오른쪽에서 1의 개수 를 헤아려 넣는다.
		
		for(int i=1; i<=N; i++)
			sb.append(res[i]).append(' ');
		
		System.out.print(sb);		
	}
}