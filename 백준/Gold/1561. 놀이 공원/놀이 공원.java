//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1561
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();				// 아이들수(1<=20억)
		int M		= read();				// 놀이기구수(1<=만)
		int arr[]	= new int[M];			// 운행시간(1<=30)
		
		for(int i=0; i<M; i++)
			arr[i] = read();

		if(N <= M)
		{
			System.out.print(N);
			return;
		}
		
		long totalTime	= 0;				// 모든 아이가 놀이기구를 타는데 걸린 시간
		long s			= 0;
		long e			= 60_000_000_000L;
		
		while(s <= e) {
			long mid = (s + e) >> 1;		// 모든 아이가 놀이기구를 타는데 걸린 시간
			long sum = M;
			
			for(int i=0; i<M; i++)
				sum += mid / arr[i];		// 해당시간까지 놀이기구를 탄 아이들의 수
			
			if(sum < N)						// mid시간으로N명을 만들 수 없을 때 s를 추가해줌
			{
				s = mid + 1;
			}
			else							// mid시간으로 N명이 가능할 때 결과를 대입하여 조건을 만족하는 최소 mid를 구함
			{
				e = mid - 1;
				totalTime = mid;
			}
		}
		
		long time		= totalTime - 1;	// 만족시간 1분전 까지 학생들을 구함
		int childCnt	= M;				// 최초 0분은 M명이 일괄 들어가므로 M 기본 세팅
		for(int i=0; i<M; i++)
			childCnt += (time / arr[i]);	// 목표 시간 1분전까지 탄 모든 아이들을 구한다.
		
		for(int i=0; i<M; i++)
			if(totalTime % arr[i] == 0)		// 최종 시간에 탈 수 있는 아이들을 구한다 이 때 운영시간과 나눈 나머지가 0인 것들이 탈 수 있는 기계인것
			{
				if(++childCnt >= N)			// 아이들이 다 타서 N명이 되었을 때 결과 출력
				{
					System.out.print(i + 1);
					return;
				}
			}
	}
}