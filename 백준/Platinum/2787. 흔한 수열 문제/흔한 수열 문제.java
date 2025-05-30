//https://www.acmicpc.net/problem/2787
//1초 128MB
//5 2 // 수열의 크기(1<=200), 설명 개수(0<=40,000)
//1 2 3 3 // 설명 : 1 x y v - x번째 수부터, y번째 수 중 제일 큰 값은 v
//2 4 5 4 // 설명 : 2 x y v - x번째 수부터, y번째 수 중 제일 작은 값은 v
//답 : 1 2 3 4 5

import java.util.Arrays;

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
		Reader in = new Reader();
		N = in.nextInt();// 수열의 크기(1<=200)
		Q = in.nextInt();// 설명 개수(0<=40,000)
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
			int T = in.nextInt();
			int s = in.nextInt();
			int e = in.nextInt();
			int target = in.nextInt();
			
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
	static class Reader {
	    final int SIZE = 1 << 13;
	    byte[] buffer = new byte[SIZE];
	    int index, size;
	    int nextInt() throws Exception {
	        int n = 0;
	        byte c;
	        boolean isMinus = false;
	        while ((c = read()) <= 32) { if (size < 0) return -1; }
	        if (c == 45) { c = read(); isMinus = true; }
	        do n = (n << 3) + (n << 1) + (c & 15);
	        while (isNumber(c = read()));
	        return isMinus ? ~n + 1 : n;
	    }
	    boolean isNumber(byte c) {
	        return 47 < c && c < 58;
	    }
	    byte read() throws Exception {
	        if (index == size) {
	            size = System.in.read(buffer, index = 0, SIZE);
	            if (size < 0) buffer[0] = -1;
	        }
	        return buffer[index++];
	    }
	}
}