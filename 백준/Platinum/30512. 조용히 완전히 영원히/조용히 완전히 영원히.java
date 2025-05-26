//https://www.acmicpc.net/problem/30512
//1초 1024MB
//10// 노드 수 1<=100,000
//10 5 6 9 2 4 7 1 8 3// 정수 0<=1,000,000
//5// 쿼리 수 1<=100,000
//1 2 8// L, R, X(0<=1,000,000)
//2 4 6
//5 9 4
//8 10 1
//3 6 7
//쿼리 수행 후 총 결과와, 각 업데이트당 잊힌 원소 개수 출력
//8 5 6 6 2 4 4 1 1 1
//6 7 8 10 10
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{

	static final int MAX = 1<<30;
	static int N, Q;
	static int arr[];
	static Node lazy[];
	static Node tree[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		tree = new Node[N * 4];
		lazy = new Node[N * 4];
		
		for(int i=0; i<tree.length; i++)
		{
			tree[i] = new Node(MAX,0);
			lazy[i] = new Node(MAX,0);
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		init(1, 1, N);
		
		Q = Integer.parseInt(br.readLine());
		for(int i=1; i<=Q; i++)
		{
			st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());//(0<=1,000,000)
			update(1, 1, N, L, R, X, i);
		}
		
		StringBuilder sb = new StringBuilder();
		
		int prefixSum[] = new int[Q + 1];
		for(int i=1; i<=N; i++)
		{
			Node now = query(1, 1, N, i);
			prefixSum[now.lastQuery] += 1;
			sb.append(now.num).append(' ');
		}
		
		sb.append('\n');
		for(int i=1; i<=Q; i++)
			sb.append(prefixSum[i] += prefixSum[i-1]).append(' ');
		
		System.out.print(sb);
	}
	static Node query(int treeNode, int s, int e, int targetIdx) {
		
		propagate(treeNode, s, e);
		
		if(s == e)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		if(targetIdx <= mid)
			return query(treeNode << 1, s, mid, targetIdx);
		
		return query(treeNode << 1 | 1, mid + 1, e, targetIdx);
	}
	static void update(int treeNode, int s, int e, int left, int right, int value, int queryIdx) {
		propagate(treeNode, s, e);
		if(e < left || right < s)
			return;
		
		if(left <= s && e <= right)
		{
			lazy[treeNode].num = value;
			lazy[treeNode].lastQuery = queryIdx;
			propagate(treeNode, s, e);
			return;
		}
		
		int mid = (s + e) >> 1;
		
		update(treeNode << 1, s, mid, left, right, value, queryIdx);
		update(treeNode << 1 | 1, mid + 1, e, left, right, value, queryIdx);
	}
	static void propagate(int treeNode, int s, int e) {
		if(lazy[treeNode].lastQuery != 0)
		{
			if(tree[treeNode].num > lazy[treeNode].num)
			{
				tree[treeNode].num = lazy[treeNode].num;
				tree[treeNode].lastQuery = lazy[treeNode].lastQuery;
			}
			if(s != e)
			{
				int left = treeNode << 1;
				int right = treeNode << 1 | 1;
				
				if(lazy[left].num > lazy[treeNode].num)
				{
					lazy[left].num = lazy[treeNode].num;
					lazy[left].lastQuery = lazy[treeNode].lastQuery;
				}
				if(lazy[right].num > lazy[treeNode].num)
				{
					lazy[right].num = lazy[treeNode].num;
					lazy[right].lastQuery = lazy[treeNode].lastQuery;
				}
				
			}
			lazy[treeNode].num = MAX;
			lazy[treeNode].lastQuery = 0;
		}
	}
	static void init(int treeNode, int s, int e) {
		if(s == e) {
			tree[treeNode].num = arr[s];
			return;
		}
		int mid = (s + e) >> 1;
		
		init(treeNode << 1, s, mid);
		init(treeNode << 1 | 1, mid + 1, e);
	}
	static class Node{
		int num,lastQuery;
		Node(int n, int l){
			num = n;
			lastQuery = l;
		}
	}
}