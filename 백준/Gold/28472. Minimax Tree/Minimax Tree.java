//https://www.acmicpc.net/problem/28472
//1초 1024MB
//12 1 // 트리 정점 수(2<=N<=10^5), 루트의 번호(1<=R<=N)
//1 2 // 정점 수 -1 개의 줄에 걸쳐 트리 간선 양끝점 u,v가 주어짐(u != v)
//1 3
//1 4
//2 5
//3 6
//4 7
//4 8
//5 9
//6 10
//6 11
//7 12
//5	// 리프 노드의 개수 (1<=L<=N)
//8 10 // 리프노드 번호(1<=ki<=N), 노드의 값(0<=ti<=10^9)
//9 1
//10 0
//11 2
//12 15
//4	// 구해야 할 노드의 개수(1<=Q<=10^5)
//1	// 구할 노드 번호
//3
//4
//5
//// 구해야 할 노드 개수 만큼 각 노드의 값을 출력한다.
//10
//2
//10
//1

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static int N, R, L;
	static int[] val;
	static List<Integer> adList[];// 양방향 노드
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 트리 정점 수(2<=N<=10^5)
		R = Integer.parseInt(st.nextToken());// 루트의 번호(1<=R<=N)
		val = new int[N + 1];
		adList = new ArrayList[N + 1];

		for(int i=0; i<=N; i++)
			adList[i] = new ArrayList<>();//초기화

		// 양방향 간선 생성
		for(int i=1; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adList[a].add(b);
			adList[b].add(a);
		}
		
		L = Integer.parseInt(br.readLine());// 리프 노드의 개수 (1<=L<=N)

		for(int i=0; i<L; i++)
		{
			st = new StringTokenizer(br.readLine());
			int leaf = Integer.parseInt(st.nextToken());// 리프노드 번호(1<=ki<=N)
			val[leaf] = Integer.parseInt(st.nextToken());// 노드의 값(0<=ti<=10^9)
		}
		
		dfs(R, -1, true);
		
		StringBuilder sb = new StringBuilder();
		
		int cnt = Integer.parseInt(br.readLine());// 구해야 할 노드의 개수(1<=Q<=10^5)
		
		while(cnt-->0)
			sb.append(val[Integer.parseInt(br.readLine())])
				.append('\n');
		
		System.out.print(sb);
	}
	static void dfs(int now, int parent, boolean isMAX) {
		// 현재가 MAX를 담는 규칙이면 0을 세팅, 아니면 큰 값을 세팅
		int child = isMAX ? 0 : Integer.MAX_VALUE;
		
		boolean isLeaf = true;// 현재 노드가 리프노드인지 체크
		
		for(int next : adList[now])// 인접노드 탐색
		{
			if(next == parent)// 이미 방문한 노드면 스킵
				continue;
			
			isLeaf = false;// 자식노드가 있다는 말은 리프노드가 아니라는 말
			
			dfs(next, now, !isMAX);// dfs 진행
			
			if(isMAX)// 현재 노드가 MAX 규칙이면, child 값에 가장 큰 값을 저장
				child = Math.max(child, val[next]);
			else// 현재 노드가 MIN 규칙이면 child 값에 가장 작은 값을 저장
				child = Math.min(child, val[next]);
		}
		
		if(!isLeaf)// 리프노드가 아닌 경우만
			val[now] = child;
	}
}