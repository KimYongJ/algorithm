//https://www.acmicpc.net/problem/18251
//2초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int xPos;
	static int N;
	static int arr[];
	static Node info[];
	
	public static void main(String[] args)throws Exception{
		input();
		dfs(1, 1);
		solve();
	}
	static void input()throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());// 노드 수 1<=262
		arr = new int[N + 1];
		info = new Node[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());// 노드 가중치 범위 : |10^9|
	}
	static void dfs(int node, int yPos) {
		int left = node * 2;
		int right = node * 2 + 1;
		
		if(left <= N)
			dfs(left, yPos + 1);
		// 중위 탐색으로 X좌표에 따른 Y좌표와 가중치를 저장
		info[++xPos] = new Node(yPos, arr[node]);
		
		if(right <= N)
			dfs(right, yPos + 1);
		
	}
	static void solve()
	{
		long sum = Long.MIN_VALUE;
		int height = 0;
		
		int x = N; // 트리의 높이를 구하기 위해 사용
		while(x > 0) {x>>=1; height++;}// 트리의 높이를 구함
		
		for(int s=1; s<=height; s++)// 모든 Y좌표 완전 탐색
		{
			for(int e=s; e<=height; e++)// 모든 Y좌표 완전 탐색
			{
				long cur = 0;
				
				for(int i=1; i<=N; i++)// X좌표를 1부터 끝까지 순차 탐색
				{
					if(info[i].yPos < s || e < info[i].yPos)// 해당 X좌표에 따른 Y좌표가 범위밖이면 스킵
						continue;
					
					// 이하 연속하는 최대 부분합을 구하기 위한 카데인 알고리즘 및 최종 결과 세팅
					cur = Math.max(cur + info[i].val, info[i].val);
					sum = Math.max(cur, sum);
				}
			}
		}
		
		System.out.print(sum);
	}
	static class Node{
		int yPos, val;
		Node(int y, int v){
			yPos = y;
			val = v;
		}
	}
}