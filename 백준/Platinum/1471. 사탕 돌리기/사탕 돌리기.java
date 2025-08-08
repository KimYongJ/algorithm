//https://www.acmicpc.net/problem/1471
//2초 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main{
	
	static int N;
	static int max;
	static int dp[];
	static int adNode[];
	static List<Cycle> cycleNodeList;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1];
		adNode = new int[N + 1];
		cycleNodeList = new ArrayList<>();

		setAdNode(); // N을 통해 각각 연결노드를 만든다.
		getCycleList();// 1번부터 N번까지 순차 탐색을하며 사이클이 발생하는 시작노드와 사이클내의 칸수를 cycleNodeList에 저장한다.
		setCycleList();// cycleNodeList를 돌면서 해당 사이클내에 있는 모든 노드에 해당 사이클의 크기를 대입한다.
		setAnother();// 사이클외에 사이클로 다가가거나, 별도 경로일 경우 dp에 탐색 크기를 마킹한다.
		
		System.out.print(max);
	}
	static void setAdNode() {
		boolean visit[] = new boolean[N + 1];
		
		for(int i=1; i<=N; i++)// 모든 노드를 돌면서 인접 노드를 생성
		{
			if(visit[i]) // 방문한 적있다면 연산하지 않음
				continue;

			visit[i] = true;
			dfs(i, visit);
		}
	}
	static void dfs(int now, boolean visit[]) {
		int next = getNext(now);
		
		adNode[now] = next;
		
		if(visit[next])
			return;

		visit[next] = true;
		dfs(next, visit);
	}
	static void getCycleList() {
		int visitTime[] = new int[N + 1];// 같은 dfs탐색안에 있는지 확인하기 위한 변수
		int order[] = new int[N + 1];// 방문 순서 마킹
		int time = 0;
		for(int i=1; i<=N; i++)
		{
			if(visitTime[i] != 0)
				continue;
			
			visitTime[i] = ++time;
			order[i] = 1;
			searchCycle(i, 1, time, visitTime, order);
		}
	}
	static void searchCycle(int now, int flag, int time, int visitTime[], int order[]) {
		int next = adNode[now];

		if(visitTime[next] == 0) // 방문한 적이 없다면 dfs 진행
		{
			visitTime[next] = time;// 방문 시간 마킹, 방문 시간은 한 탐색동안 모두 같은 값이다.
			order[next] = ++flag;// 방문 순서 마킹, 방문 순서는 순차증가하는 번호로 저장한다 그래야 나중에 같은 시간대에 탐색한 사이클과의 거리차를 알 수 있음
			searchCycle(next, flag, time, visitTime, order);
		}
		else if(visitTime[next] == time)// 방문을 했는데, 같은 시간대에 탐색한 것이라면
		{
			int cnt = Math.abs(order[now] - order[next]) + 1;// 사이클의 거리차를 구한다.
			cycleNodeList.add(new Cycle(now, cnt));// 사이클의 시작노드와 해당 사이클의 모든 노드의 수를 저장
		}

	}
	static void setCycleList() {
		for(Cycle now : cycleNodeList)// 사이클의 시작노드와 사이클내에 있는 노드의 수의 총합을 해당 사이클 모두에 마킹 
			mark(now.startNode, now.startNode, now.cnt);
	}
	static void mark(int node, int startNode, int cnt) {
		dp[node] = cnt; // 마킹
		
		int next = adNode[node]; // 다음 노드
		
		if(next != startNode)// 다음 노드가 시작 노드가 아니라면 dfs 탐색
			mark(next, startNode, cnt);
	}
	static void setAnother() {
		for(int i=1; i<=N; i++)
		{
			if(dp[i] == 0)// 아직 탐색하지 않은 노드일 경우
				dfs2(i);
			
			max = Math.max(dp[i], max);
		}
	}
	static int dfs2(int now) {
		if(dp[now] != 0)
			return dp[now];
		
		dp[now] = 1;
		
		if(adNode[now] != 0)
			dp[now] += dfs2(adNode[now]);
		
		return dp[now];
	}
	static int getNext(int origin) {
		int sum = origin;
		int a = origin;
		while(a != 0)
		{
			sum += a%10;
			a/=10;
		}
		
		if(sum == N)
			return sum;
		
		return sum % N;
	}
	static class Cycle{
		int startNode;
		int cnt;
		Cycle(int s, int c){
			startNode = s;
			cnt = c;
		}
	}
}