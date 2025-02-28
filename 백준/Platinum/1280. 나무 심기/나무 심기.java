//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1280
//2초 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static final long MOD = 1_000_000_007;
	static final int N = 200_000;
	static int T;
	static long[] ctree, stree;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T		= Integer.parseInt(br.readLine());//2<=이십만
		ctree	= new long[N << 2];
		stree	= new long[N << 2];
		
		long res = 1;
		// 첫번째는 비용이 들지 않기 때문에 따로 해야함, res에 영향을 주지 않기위해.
		update(1, 0, N, Integer.parseInt(br.readLine()));
		for(int i=2; i<=T; i++)
		{
			int pos = Integer.parseInt(br.readLine());// 0<=이십만
			
			long leftCnt	= query(1, 0, N, 0, pos - 1, ctree);
			long leftSum	= query(1, 0, N, 0, pos - 1, stree);
			long rightCnt	= query(1, 0, N, pos + 1, N, ctree);
			long rightSum	= query(1, 0, N, pos + 1, N, stree);
			long cal = (pos * leftCnt - leftSum) +(rightSum - pos * rightCnt);

			cal %= MOD;
            res = (res * cal) % MOD;
			
			update(1, 0, N, pos);
		}
		System.out.print(res);
	}
	public static long query(int treeNode, int s, int e, int left, int right, long[] tree) {
		if(e < left || right < s)
			return 0;
		if(left<=s && e<=right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		return query(treeNode << 1, s, mid, left, right, tree)
				+ query(treeNode << 1 | 1, mid + 1, e, left, right, tree);
	}
	public static void update(int treeNode, int s, int e, int idx) {
		if(idx < s || e < idx) return;
		if(s == e)
		{
			ctree[treeNode] += 1;// 같은 좌표에 심길 수 있으므로 더하기 연산해야함
			stree[treeNode] += s;// 같은 좌표에 심길 수 있으므로 더하기 연산해야함
			return;
		}
		
		int mid = (s + e) >> 1;
		
		update(treeNode << 1, s, mid, idx);
		update(treeNode << 1 | 1, mid + 1, e, idx);
		
		ctree[treeNode] = ctree[treeNode << 1] + ctree[treeNode << 1 | 1];
		stree[treeNode] = stree[treeNode << 1] + stree[treeNode << 1 | 1];
	}
}
