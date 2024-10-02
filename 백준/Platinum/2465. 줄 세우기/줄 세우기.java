//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2465
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	
	static int[] res, arr, tree, tall, parent;
	public static int init(int treeNode, int s, int e) {
		if(s == e)
			return tree[treeNode] = 1;
		int mid = (s + e) >> 1;
		return tree[treeNode] = init(treeNode*2, s, mid) + init(treeNode*2+1, mid + 1, e);
	}

	public static int query(int treeNode, int s, int e, int cnt) {
		// 쿼리 실행 위치마다 1을 마이너스해주어 추후 update연산을 간소화 시킨다.
		tree[treeNode]--;// 항상 답을 구할 것이기 때문에 그 답에 대해 항상 -1을 해야하는 update문이 필요하므로 미리 빼주는것
		
		if(s == e)
			return s;
		
		int mid = (s + e) >> 1;
		return cnt < tree[treeNode * 2]			? 
				query(treeNode*2, s, mid, cnt)	: 
				query(treeNode*2+1,mid+1, e, cnt - tree[treeNode * 2]);
	}
	public static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 사람수 N (1<=십만)
		int H = (int)Math.ceil(Math.log(N+1) / Math.log(2));
		arr = new int[N+1];
		res = new int[N+1];
		tall= new int[N+1];
		tree= new int[1 << (H + 1)];
		parent = new int[N+2];
		for(int i=1; i<=N; i++)
		{
			arr[i] = Integer.parseInt(br.readLine());// 입력키 (0<=이십억)
			parent[i] = i;
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			tall[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(arr);
		
		init(1,1,N);

		for(int i=N; i>=1; i--)
		{
			int myIdx		= tall[i];
			int idx			= query(1, 1, N, myIdx);
			int nextIdx 	= find(idx);
			parent[nextIdx] = find(nextIdx + 1);
			res[i] = arr[nextIdx];
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++)
			sb.append(res[i]).append('\n');
		
		System.out.print(sb.toString());
	}
}