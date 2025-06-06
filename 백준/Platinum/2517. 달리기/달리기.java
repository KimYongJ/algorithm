//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2517
//1초 / 256MB
//요약 : 입력되는 순서대로 자기 앞에 자기보다 큰수가 몇개 나왔는지 세고, 그 숫자에 + 1을 출력
import java.util.HashMap;
import java.util.PriorityQueue;
class Main{
	
	static int N;
	static int[] arr, tree;
	static HashMap<Integer, Integer> map = new HashMap<>();
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		N	= read();	// 3<=오십이하
		arr	= new int[N];
		tree= new int[N<<2];
		for(int i=0; i<N; i++)
			pq.add(arr[i] = read());	// 십억이하
		
		int rank = 0;
		while(!pq.isEmpty())
			map.put(pq.poll(), ++rank);
		
		for(int a : arr)
		{
			int idx = map.get(a);
			
			sb.append(query(1, 1, N, idx+1, N)+ 1).append('\n');
			
			update(1, 1, N, idx);
		}
		System.out.print(sb);
	}
	public static void update(int treeNode, int s, int e, int idx) {
		if(idx < s || e< idx)
			return;
		
		tree[treeNode]++;
		
		if(s != e)
		{
			int nextNode = treeNode << 1;
			int mid = (s + e) >> 1;
			update(nextNode, s, mid, idx);
			update(nextNode | 1, mid + 1, e, idx);
		}
	}
	public static int query(int treeNode, int s, int e, int left, int right) {
		if(e < left || right < s)
			return 0;
		if(left<=s && e<=right)
			return tree[treeNode];
		
		int nextNode = treeNode << 1;
		int mid = (s + e) >> 1;
		return query(nextNode, s, mid, left, right) 
				+ query(nextNode | 1, mid + 1, e, left, right);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}