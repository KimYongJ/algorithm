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
			updated(node*2, s, mid, targetIdx, diff);
			updated(node*2 +1, mid+1, e, targetIdx, diff);
		}
	}
	public static long query(int node, int left, int right, long target) {
		if(left == right)	// 루트노드로 무조건 내려오게 되고, 내려오는 과정에서 해당 target에 해당하는 루트로 내려오게 만드는게 중요
		{
			updated(1, 1, size, left, -1);	// target에 해당하는 루트로 온경우 해당 위치의 사탕 하나를 삭제한다.
			return left;	// 해당 순위 반환
		}
		
		int mid = (left + right) / 2;
		if(target <= tree[node * 2])// 현재 node의 왼쪽 노드(node*2)의 값이 찾으려는 target 보다 누적합이 작거나 같다면 왼쪽으로 이동 
			return query(node*2, left, mid, target);
		else
			return query(node*2 + 1,mid + 1, right, target - tree[node*2]);
		
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());	// 사탕상자에 손을 댄 횟수(1<=십만)
		
		int h = (int)Math.ceil(Math.log(size) / Math.log(2));
		int treeSize = (int)Math.pow(2, h + 1);
		tree = new int[treeSize];
		
		while(T-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			if(cmd == 1)	// 사탕을 꺼내는 경우
			{
				int rnk = Integer.parseInt(st.nextToken()); //꺼낼 사탕의 순위
				sb.append(query(1, 1, size, rnk)).append('\n');
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