//https://www.acmicpc.net/problem/2787
//1초 128MB
//5 2 // 수열의 크기(1<=200), 설명 개수(0<=40,000)
//1 2 3 3 // 설명 : 1 x y v - x번째 수부터, y번째 수 중 제일 큰 값은 v
//2 4 5 4 // 설명 : 2 x y v - x번째 수부터, y번째 수 중 제일 작은 값은 v
//답 : 1 2 3 4 5

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	
	static int N, Q;
	static int rangeMax[];
	static int rangeMin[];
	static boolean isPossible[][];
	// 이분 매칭시 사용
	static int time;
	static int match[];
	static int visitTime[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 수열의 크기(1<=200)
		Q = Integer.parseInt(st.nextToken());// 설명 개수(0<=40,000)
		match = new int[N + 1];
		rangeMax = new int[N + 1];
		rangeMin = new int[N + 1];
		visitTime = new int[N + 1];
		isPossible = new boolean[N + 1][N + 1];// 각 인덱스 범위에서 가능한 숫자목록, 처음에는 1부터 N까지 인덱스에서 1부터N까지 모두 가능하다.
		
		for(int n=1; n<=N; n++)
		{
			Arrays.fill(isPossible[n], true);
			rangeMax[n] = N;// 인덱스당 최댓값은 N
			rangeMin[n] = 1;// 인덱스당 최솟값은 1
		}
		
		for(int i=0; i<Q; i++)
		{
			st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int target = Integer.parseInt(st.nextToken());
			
			for(int idx=1; idx<=N; idx++)
			{
				if(s <= idx && idx <= e)// idx 범위 안에서 해당 idx의 최소 최댓값 저장
				{
					if(T==1)// s번째 수부터, e번째 수 중 제일 큰 값은 target
						rangeMax[idx] = Math.min(rangeMax[idx], target);
					else// s번째 수부터, e번째 수중 제일 작은 값은 target
						rangeMin[idx] = Math.max(rangeMin[idx], target);
					
					continue;
				}
				// s, e범위를 벗어나면 해당 idx에서 그 target은 못쓰기 때문에 false 처리
				isPossible[idx][target] = false;
			}
		}
		
		for(int idx = 1; idx<=N; idx++)
		{
			int max = rangeMax[idx];// 해당 index의 가능한 가장 큰것
			int min = rangeMin[idx];// 해당 index의 가능한 가장 작은것
			
			for(int i = 1; i<min; i++)// min보다 작은 것은 해당 index에서 안쓰기 때문에 false처리
				isPossible[idx][i] = false;
		
			for(int i = max + 1; i<=N; i++)// max보다 큰 것은 해당 index에서 안쓰기 때문에 false처리
				isPossible[idx][i] = false;
		}
		
		int cnt = 0;
		
		for(int i=1; i<=N; i++)
		{
			++time;
			if(dfs(i))
				++cnt;
		}
		
		if(cnt != N)
		{
			System.out.print(-1);
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=N; i++)
			sb.append(match[i]).append(' ');
		
		System.out.print(sb);
	}
	static boolean dfs(int target)
	{
		for(int idx=1; idx<=N; idx++)
		{
			if(!isPossible[idx][target] || visitTime[idx] == time)
				continue;
			
			visitTime[idx] = time;
			
			if(match[idx] == 0 || dfs(match[idx]))
			{
				match[idx] = target;
				return true;
			}
		}
		return false;
	}
}