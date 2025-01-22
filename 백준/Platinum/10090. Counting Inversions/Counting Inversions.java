//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/10090
//1초 / 256MB
//요약 : 배열의 왼쪽부터 시작하여 오른쪽으로 갈때 자기보다 작은 쌍이 몇개나있는지 쌍의 총개수 출력
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static int N, tree[];
	public static int init(int treeNode, int s, int e) {
		if(s == e)
			return tree[treeNode] = 1;
		
		int nextNode= treeNode << 1;
		int mid		= (s + e) >> 1;
		
		return tree[treeNode] 
				= init(nextNode, s, mid) + init(nextNode | 1, mid + 1, e);
	}
	public static int query(int treeNode, int s, int e, int left, int right) {
		if(e < left || right < s)
			return 0;
		if(left<=s && e<=right)
			return tree[treeNode];
		
		int nextNode= treeNode << 1;
		int mid		= (s + e) >> 1;
		
		return query(nextNode, s, mid, left, right)
				+ query(nextNode | 1, mid + 1, e, left, right);
	}
	public static void update(int treeNode, int s, int e, int idx) {
		if(idx < s || e < idx)
			return;
		
		tree[treeNode] -= 1;
		
		if(s != e)
		{
			int nextNode= treeNode << 1;
			int mid		= (s + e) >> 1;
			update(nextNode, s, mid, idx);
			update(nextNode | 1, mid + 1, e, idx);
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N	= Integer.parseInt(br.readLine());
		tree= new int[N<<2];
		
		init(1, 1, N);
		
		long sum = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
		{
			int target = Integer.parseInt(st.nextToken());
			sum += query(1, 1, N, 1, target - 1);
			update(1, 1, N, target);
		}
		System.out.print(sum);
	}
}