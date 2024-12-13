//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2450
class Main{
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		final int comb[][] = {{1,2,3},{1,3,2},{2,1,3},{2,3,1},{3,1,2},{3,2,1}};
		int N		= read();
		int arr[]	= new int[N];
		int CNT[]	= new int[4];
		
		for(int i=0; i<N; i++)
			CNT[arr[i] = read()]++;
		
		int min = 1<<30;
		for(int c[] : comb)
		{
			int changeCnt[] = new int[4];
			int cnt = 0;
			int len = CNT[c[0]];
			int len2= len + CNT[c[1]];
			
			// 첫번째 구간 나와야할 숫자가 안나오면, 첫번 째 구간 숫자가 아닌 숫자에 +1을 더해 마킹한다.
			for(int i=0; i<len; i++)
				if(arr[i] != c[0])
				{
					++changeCnt[arr[i]];// 바꾼 숫자를 마킹한다.
					++cnt;				// 바꾼 횟수 추가
				}
			// 두번 째 구간 탐색
			for(int i=len; i<len2; i++)
			{
				if(arr[i] == c[0])			// 두번째 구간에서 첫번 째 숫자를 만난 경우
				{
					if(changeCnt[c[1]] != 0)// 첫번째 구간에서 두번째 숫자를 만난 적이있다면,
						--changeCnt[c[1]];	// 교체한 카운트 차감
					else ++cnt;				// 만난 적이 없다면 무조건 바꿔야 하므로 cnt+1
				}
				// 두번 째 구간에서 세번째 숫자를 만난 경우 무조건 바꿔야 하므로 cnt+1
				else if(arr[i] == c[2])
					++cnt;
			}
			
			// 최소값 갱신
			if(cnt < min)
				min = cnt;
		}
		System.out.print(min);
	}
}
