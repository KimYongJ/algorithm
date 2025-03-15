//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/16978
//1초 / 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
class Node{
	int key;
	int k, i, j;
	long sum;
	public Node(int key, int k, int i, int j, long sum) {
		this.key = key;
		this.k = k;
		this.i = i;
		this.j = j;
		this.sum=sum;
	}
}
class Order{
	int i, v;
	public Order(int i, int v) {
		this.i=i;
		this.v=v;
	}
}
class Main{

	static int N;
	static long[] tree, arr;
	public static void init(int treeNode, int s, int e) {
		if(s == e)
		{
			tree[treeNode] = arr[s];
			return;
		}
		
		int mid = (s + e) >> 1;
		int nxt = treeNode << 1;
		
		init(nxt, s, mid);
		init(nxt + 1, mid + 1, e);
		
		tree[treeNode] = tree[nxt] + tree[nxt | 1];
	}
	public static void update(int treeNode, int s, int e, int idx, long value) {
		if(idx < s || e < idx)
			return;
		if(s == e)
		{
			tree[treeNode] = value;
			return;
		}
		
		int mid = (s + e) >> 1;
		int nxt = treeNode << 1;
		
		update(nxt, s, mid, idx, value);
		update(nxt + 1, mid + 1, e, idx, value);
		
		tree[treeNode] = tree[nxt] + tree[nxt | 1];
	}
	public static long query(int treeNode, int s, int e, int left, int right) {
		if(right < s || e < left)
			return 0;
		if(left<=s && e<= right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		int nxt = treeNode << 1;
		
		return query(nxt, s, mid, left, right) +
				query(nxt + 1, mid + 1, e, left, right);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N	= Integer.parseInt(st.nextToken());
		tree= new long[N * 4];
		arr	= new long[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		ArrayList<Node> list = new ArrayList<>();
		ArrayList<Order> order= new ArrayList<>();
		int Q = Integer.parseInt(br.readLine());
		for(int key=1; key<=Q; key++)
		{
			st = new StringTokenizer(br.readLine());
			int f = Integer.parseInt(st.nextToken());
			if(f == 1)
			{
				int i = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				order.add(new Order(i,v));
			}
			else
			{
				int k = Integer.parseInt(st.nextToken());
				int i = Integer.parseInt(st.nextToken());
				int j = Integer.parseInt(st.nextToken());
				list.add(new Node(key,k,i,j,0));
			}
		}
		Collections.sort(list, (a,b)-> a.k - b.k);
		
		init(1, 1, N);
		int idx = 0;
		while(idx < list.size() && list.get(idx).k == 0)
		{
			Node o = list.get(idx++);
			o.sum = query(1, 1, N, o.i, o.j);
		}
		
		for(int i=1; i<=order.size(); i++) {
			Order ord = order.get(i-1);
			update(1, 1, N, ord.i, ord.v);
			while(idx < list.size() && list.get(idx).k == i)
			{
				Node o = list.get(idx++);
				o.sum = query(1, 1, N, o.i, o.j);
			}
		}
		
		Collections.sort(list, (a,b)-> a.key - b.key);
		for(Node o : list)
			sb.append(o.sum).append('\n');
		System.out.print(sb);
	}
}