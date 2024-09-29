//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2243
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static final int size = 1_000_000;
	static int tree[];
	
	public static void update(int node, int s, int e, int originIdx, int diff) {
		if(originIdx < s || e < originIdx)
			return;
		
		tree[node] += diff;
		
		if(s != e)
		{
			int mid = (s + e) >> 1;
			update(node*2, s, mid, originIdx, diff);
			update(node*2 + 1, mid + 1, e, originIdx, diff);
		}
	}
	public static int getRank(int node, int s, int e, int target) {
		if(s == e)
		{
			update(1, 1, size, s, -1);
			return s;
		}
		
		int mid = (s + e) >> 1;
		if(target <= tree[node * 2])
			return getRank(node * 2, s, mid, target);
		else
			return getRank(node * 2 + 1, mid + 1, e, target - tree[node * 2]);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());	// 사탕상자에 손을댄 횟수(1<=십만)
		
		int h = (int)Math.ceil(Math.log(size) / Math.log(2));
		tree = new int[(int)Math.pow(2,h + 1)];
		
		while(N-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			if(cmd == 1)	// 사탕을 꺼내는 경우
			{
				int rnk = Integer.parseInt(st.nextToken());
				sb.append(getRank(1, 1, size, rnk)).append('\n');
			}
			else			// 사탕을 넣는 경우
			{
				int idx = Integer.parseInt(st.nextToken());	// 1~백만 까지의 맛 중, 어느 맛인지
				int cnt = Integer.parseInt(st.nextToken());	// 몇개를 넣을 것인지
				update(1, 1, size, idx, cnt);
			}
		}
		System.out.print(sb);
	}
}