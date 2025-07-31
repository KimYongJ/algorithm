//https://www.acmicpc.net/problem/14926
//1초 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	
	static int N;
	static int idx[];
	static boolean visit[][];
	static StringBuilder sb;
	
	public static void main(String args[])throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		idx = new int[N + 1];
		visit = new boolean[N + 1][N + 1];
		sb = new StringBuilder();
		
		for(int i=1; i<=N; i++)
		{
			idx[i] = 1;
			visit[i][i] = true;// 자기자신 방문 처리
		}
		// 1번과 N번은 미리 연결을 해놓아 중복탐색을 막는다.
		visit[1][N] = visit[N][1] = true;

		sb.append("a1 ");
		
		
		int cur = 1;

		while(true)
		{
			int idxCur = idx[cur];
			
			while(idxCur + 1 <= N && visit[cur][idxCur])
				++idxCur;
			
			if(visit[cur][idxCur])
				break;
			
			visit[cur][idxCur] = visit[idxCur][cur] = true;
			
			sb.append("a").append(idxCur).append(' ');
			// 다음 탐색시 불필요하게 1부터 탐색하지 않도록 dp형식으로 탐색번호를 저장해 놓음
			cur = idx[cur] = idxCur;
		}
		
		sb.append("a1");
		
		System.out.print(sb);
	}
}