//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2243

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	static final int size = 1_000_000;
	static int tree[];
	public static void updated(int node, int s, int e, int targetIdx, int diff) {
		if(targetIdx < s || e< targetIdx)
			return;
		tree[node] += diff;
		if(s != e)
		{
			int mid = (s + e) >> 1;
			updated(node*2, s , mid, targetIdx, diff);
			updated(node*2 +1, mid+1, e, targetIdx, diff);
		}
	}
	public static long query(int left, int right, int node, long target) {
		if(left == right)
		{
			updated(1, 1, size, left, -1);
			return left;
		}
		
		int mid = (left + right) / 2;
		if(tree[node * 2] >= target) {
			return query(left, mid, node*2, target);
		}else
			return query(mid + 1, right, node*2 + 1, target - tree[node*2]);
		
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());	// 사탕상자에 손을 댄 횟수(1<=십만)
		
		tree = new int[size<<2];
		
		
		for(int i=0; i<N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			if(cmd == 1)	// 사탕을 꺼내는 경우
			{
				int rnk = Integer.parseInt(st.nextToken()); //꺼낼 사탕의 순위
				sb.append(query(1, size, 1, rnk)).append('\n');
			}
			else			// 사탕을 넣는 경우
			{
				int b = Integer.parseInt(st.nextToken());	// 넣을 사탕의 맛
				int c = Integer.parseInt(st.nextToken());	// 넣을 개수
				updated(1, 1, size, b, c);
			}
		}
				
		
		System.out.print(sb);
	}
}