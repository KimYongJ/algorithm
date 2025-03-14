//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/12899
//2초 / 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static final int LEN = 2_000_000;
	static int N;
	static int[] tree;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		tree = new int[LEN * 4];
		while(N-->0)
		{
			st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());
			if(T==1)
				update(1, 1, LEN, X);
			else
				sb.append( query(1, 1, LEN, X) )
					.append('\n');
		}
		System.out.print(sb);
	}
	public static int query(int treeNode, int s, int e, int cnt) {
		tree[treeNode]--;
		
		if(s == e)
			return s;
		
		int mid = (s + e) >> 1;
		int next = treeNode << 1;
		
		return tree[next] >= cnt ?
				query(next, s, mid, cnt) :
				query(next | 1, mid + 1, e, cnt - tree[next]);
	}
	public static void update(int treeNode, int s, int e, int idx) {
		if(idx < s || e < idx)
			return;
		
		tree[treeNode]++;
		
		if(s == e)
			return;
		
		int mid = (s + e) >> 1;
		int next = treeNode << 1;
		
		update(next, s, mid, idx);
		update(next | 1, mid + 1, e, idx);
	}
}