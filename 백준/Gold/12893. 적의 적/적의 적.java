//https://www.acmicpc.net/problem/12893
//2초 512MB
//3 3// 용재 주위 사람수(1<=2,000), 적대관계수(0<=1,000,000)
//1 2// 적대관계에 있는 사람의 번호가 주어짐
//2 3
//1 3
//이론이 성립하면 1, 아니면 0 출력
//답 : 0

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static int res = 1;
	static List<Integer> adList[];
	static int color[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());// 용재 주위 사람수(1<=2,000)
		int M = Integer.parseInt(st.nextToken());// 적대관계수(0<=1,000,000)
		adList = new ArrayList[N + 1];
		color = new int[N + 1];
		
		for(int i=0; i<=N; i++)
		{
			adList[i] = new ArrayList<>();
			color[i] = -1;
		}
		
		int startNode = 0;
		for(int i=0; i<M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adList[a].add(b);
			adList[b].add(a);
			startNode = a;
		}
		
		dfs(startNode, 1);
		
		System.out.print(res);
	}
	static void dfs(int nowNode, int nowColor)
	{
		
		if(res == 0)
			return;
		
		color[nowNode] = nowColor;
		
		for(int next : adList[nowNode])
		{
			if(color[next] == -1)
				dfs(next, nowColor ^ 1);
			else if(color[next] == nowColor)
			{
				res = 0;
				break;
			}
		}
	}
}