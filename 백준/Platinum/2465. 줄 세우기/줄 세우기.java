//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2465
import java.util.Arrays;
class Main{
	
	static int[] res, arr, tree, tall;
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static int init(int treeNode, int s, int e) {
		if(s == e)
			return tree[treeNode] = 1;
		int mid = (s + e) >> 1;
		return tree[treeNode] = init(treeNode*2, s, mid) + init(treeNode*2+1, mid + 1, e);
	}
	public static int query(int treeNode, int s, int e, int cnt) {
		// 쿼리 실행 위치마다 1을 마이너스해주어 추후 update연산을 간소화 시킨다.
		tree[treeNode]--;// 항상 답을 구할 것이기 때문에 그 답에 대해 항상 -1을 별도로 해야하지만 query탈때 update도 한꺼번에 처리해도됨
		
		if(s == e)
			return s;
		
		int mid = (s + e) >> 1;
		return cnt < tree[treeNode * 2]			? 
				query(treeNode*2, s, mid, cnt)	: 
				query(treeNode*2+1,mid+1, e, cnt - tree[treeNode * 2]);
	}
	public static void main(String[] args)throws Exception{
		int N	= read(); // 사람수 N (1<=십만)
		int H	= (int)Math.ceil(Math.log(N+1) / Math.log(2));
		arr 	= new int[N+1];
		res 	= new int[N+1];
		tall	= new int[N+1];
		tree	= new int[1 << (H + 1)];
		
		for(int i=1; i<=N; i++)
			arr[i] = read();// 입력키 (0<=이십억)

		for(int i=1; i<=N; i++)
			tall[i] = read();
		
		Arrays.sort(arr);
		// 사용되지 않은 곳들을 1로 마킹하여 cnt값을 통해 한번에 해당 위치를 찾을 수 있도록 한다.
		init(1,1,N);
		
		// 뒤에서부터 탐색하나간다.
		for(int i=N; i>=1; i--)
		{
			int myIdx	= tall[i];
			int idx		= query(1, 1, N, myIdx);
			res[i] = arr[idx];
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++)
			sb.append(res[i]).append('\n');
		
		System.out.print(sb.toString());
	}
}
