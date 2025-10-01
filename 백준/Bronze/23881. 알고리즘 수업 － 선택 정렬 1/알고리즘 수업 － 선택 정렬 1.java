//https://www.acmicpc.net/problem/23881
//1초 512MB
//5 2 // 배열크기 (5<=10000), 교환 횟수 (1<=N)
//3 1 2 5 4 // 서로다른 배열원소(1<=10^9)
//답 : 2 3

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, K;
	static int[] arr;
	static Node dummy;
	static Node[] tree;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 배열크기 (5<=10000)
		K = Integer.parseInt(st.nextToken());// 교환 횟수 (1<=N)
		arr = new int[N + 1];
		tree = new Node[N * 4];
		dummy = new Node(0,0);
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<tree.length; i++)
			tree[i] = new Node(0,0);
		
		init(1, 1, N);
		
		for(int last = N; last>1; last--)
		{
			Node node = maxQuery(1, 1, N, 1, last - 1);// 해당 범위의 가장 큰수를 가져온다.
			
			int a = arr[last];
			int b = node.num;
			int i = node.idx;
			if(a < b)// 가져온 가장 큰수가 arr[last]보다 크다면 둘을 스왑 한다.
			{
				update(1, 1, N, i, new Node(i, a));
				
				arr[i] = a;
				
				if(--K == 0)
				{
					System.out.print(a + " " + b);
					return;
				}
			}
		}
		System.out.print(-1);
	}
	static void update(int treeNode, int s, int e, int targetIdx, Node value) {
		if(e < targetIdx || targetIdx < s)
			return;
		
		if(s == e)
		{
			tree[treeNode].idx = value.idx;
			tree[treeNode].num = value.num;
			return;
		}
		
		int mid = (s + e) >> 1;
		int left = treeNode << 1;
		int right = treeNode << 1 | 1;
		
		update(left, s, mid, targetIdx, value);
		update(right, mid + 1, e, targetIdx, value);
		
		if(tree[left].num < tree[right].num)
			left = right;// left변수를 right변수로 만들어 코드 간소화
		
		tree[treeNode].num = tree[left].num;
		tree[treeNode].idx = tree[left].idx;
	}
	static Node maxQuery(int treeNode, int s, int e, int left, int right) {
		if(e < left || right < s)
			return dummy;
		
		if(left <= s && e <= right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		Node L = maxQuery(treeNode << 1, s, mid, left, right);
		Node R = maxQuery(treeNode << 1 | 1, mid + 1, e, left, right);

		return L.num < R.num ? R : L;
	}
	static Node init(int treeNode, int s, int e) {
		if(s == e)
		{
			tree[treeNode].idx = s;
			tree[treeNode].num = arr[s];
			return tree[treeNode];
		}
		
		int mid = (s + e) >> 1;
			
		Node L = init(treeNode << 1, s, mid);
		Node R = init(treeNode << 1 | 1, mid + 1, e);
		
		if(L.num < R.num)
			L = R;// L을 큰 값으로 덮어 씌움
		
		tree[treeNode].idx = L.idx;
		tree[treeNode].num = L.num;
		
		return tree[treeNode];
	}
	static class Node{
		int idx, num;
		Node(int i, int n){
			idx = i;
			num = n;
		}
	}
}