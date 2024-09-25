//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1561
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();				// 아이들 수(1<=20억)
		int M		= read();				// 기계 수
		int arr[]	= new int[M];			// 놀이기구 운행시간(1<=30);
		long s		= 1;
		long e		= 0;
		for(int i=0; i<M; i++)
		{
			arr[i] = read();
			e = Math.max(e, arr[i]);
		}
		
		if(N <= M)							// 기계보다 사람이 적거나 같다면 바로 종료
		{
			System.out.print(N);
			return;
		}
		
		long totalTime = 0;					// 모든 아이들을 다 태우는데 걸리는 시간
		e = (N * e) / M;					// 아이들수 x 가장긴 놀이기구 운행시간 / 기계 수
		
		while(s <= e) {
			long mid = (s + e) >> 1; 		// 모든 아이들을 다 태우는데 걸리는 시간
			long sum = M;					// 아이틀의 숫자의 합 (가장 처음 0초에 M명이 바로 탈 수 있으므로 기본 값은 M)
			for(int i=0; i<M; i++)
				sum += mid / arr[i];
			
			if(sum < N)
				s = mid + 1;
			else {
				e = mid - 1;
				totalTime = mid;
			}
		}
		
		long oneMinuteAgo = totalTime - 1;	// 종료 1분전 값
		long sum = M;						// 종료 1분전 아이들의 수
		for(int i=0; i<M; i++)
			sum += oneMinuteAgo / arr[i]; 	// 종료 1분전 아이들의 수를 구한다.
		
		N -= sum;							// 총 아이들과 종료 1분전 아이들을 빼서 마지막 1분간의 아이들 숫자만 N에 남긴다.
		
		for(int i=0; i<M; i++)
			if(totalTime % arr[i] == 0)		// 마지막 시간이기 때문에 무조건 해당 부분에 값을 찾을 수 있게 된다. 
				if(--N == 0)				// 나머지가 0인 아이들을 찾을 때마다 N을 1씩 감소해주고, 마지막 아이 즉, 0이 되는 순간 그 idx를 출력
				{
					System.out.print(i+1);
					return;
				}

	}
}
/*
24 5
1 2 2 4 4
출력 : 4
1987654321 2
15 14
출력 : 2
*/
