// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;


class Main
{	
	public static int BFS(int N, int K) 
	{
		ArrayDeque<Integer> q 	= new ArrayDeque<Integer>() {{add(N);}};
		boolean visit [][]		= new boolean[2][500001];// [0][x] 짝수, [1][x] 홀수
		final int MAX = 500000;
		int nowN, size, flag, nextN, plus, nextTime;
		
		visit[0][N] = true;
		nextTime 	= plus = 0;
		while(!q.isEmpty()) 
		{
			size 	= q.size();			// 큐 사이즈만큼만 누나가 이동을한다
			K 		+= ++plus;			// 다음 K의 위치를 구해놓는다.
			nextTime+=1;				// 다음 시간을 구한다
			flag 	= nextTime%2;		// 짝수인지 홀수인지 체크
			if(K > MAX)					// 동생이 50만을 넘으면 종료 
				break;
			
			while(size-->0)				// 큐사이즈 만큼 연산
			{
				nowN 	= q.poll();
				nextN = nowN-1;
				if(0<=nextN && nextN<=500000 && !visit[flag][nextN]) 
				{
					visit[flag][nextN] = true;
					q.add(nextN);
				}
				nextN = nowN+1;
				if(0<=nextN && nextN<=500000 && !visit[flag][nextN]) 
				{
					visit[flag][nextN] = true;
					q.add(nextN);
				}
				nextN = nowN*2;
				if(0<=nextN && nextN<=500000 && !visit[flag][nextN]) 
				{
					visit[flag][nextN] = true;
					q.add(nextN);
				}
			}
			if(visit[flag][K])// 동생이 간곳이 이미 누나가방문했던곳이면 그시간 출력 후 종료
				return nextTime;
		}
		return -1;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br 	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st 	= new StringTokenizer(br.readLine());
		int N 				= Integer.parseInt(st.nextToken());
		int K 				= Integer.parseInt(st.nextToken());
		if(N==K) 
		{
			System.out.println(0); 
			return;
		}
		System.out.println( BFS(N,K) );
	}
}

