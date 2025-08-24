//https://www.acmicpc.net/problem/18251
//2초 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int xPos, maxYPos;
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
		
		info[++xPos] = new Node(yPos, arr[node]);
		
		if(right <= N)
			dfs(right, yPos + 1);
		
		maxYPos = Math.max(maxYPos, yPos);
	}
	static void solve()
	{
		long sum = Long.MIN_VALUE;

		for(int s=1; s<=maxYPos; s++)
		{
			for(int e=s; e<=maxYPos; e++)
			{
				long cur = 0;
				
				for(int i=1; i<=N; i++)
				{
					if(info[i].yPos < s || e < info[i].yPos)
						continue;
					
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