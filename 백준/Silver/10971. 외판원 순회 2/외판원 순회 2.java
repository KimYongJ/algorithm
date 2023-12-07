// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		new Solution().solution();
	}
}
class Solution{
	
	int N, min_cost, arr[][];
	boolean visit[];
	void solution()throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		visit = new boolean[N];
		min_cost = Integer.MAX_VALUE;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		DFS(new int[N],0);
		
		System.out.println(min_cost);
	}
	void DFS(int[] order, int depth) {
		if(depth == N) { // 방문한 노드 갯수가 전체 다방문했다면 종료
			int cost = 0;
			for(int i=1; i<N; i++) {
				int aNode = order[i-1];
				int bNode = order[i];
				if(arr[aNode][bNode] == 0) return;
				
				cost += arr[aNode][bNode];
				if(i==N-1) {
					aNode = order[i];
					bNode = order[0];
					if(arr[aNode][bNode] == 0) return;
					cost += arr[aNode][bNode];
				}
			}
			min_cost = Math.min(min_cost, cost);
			return;
		}
		for(int i=0; i<N; i++) {
			if(!visit[i]) {
				visit[i] = true;
				order[depth]=i;
				DFS(order, depth+1);
				visit[i] = false;
			}
		}
		
	}
}