//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/24453
//0.6초 / 512MB
//요약 : 연속된 숫자를 X개 이상 만들기 위해 필요한 최소 개수

class Main{
	public static void main(String[] args)throws Exception{

		int N		= read();		// 총 코드줄수(1<=이천만)
		int M		= read();		// 오류가 있는 줄 수(1<=min(N,오십만))
		int ans		= Integer.MAX_VALUE;
		int errCnt	= 0;			// 고친에러 개수
		int connect = 0;			// 연속된 숫자 개수
		boolean v[]	= new boolean[N+1];
		
		for(int i=0; i<M; i++)
			v[read()] = true;		// 고쳐야되는 것은 true

		int X = read();				// 오류가 없는 X줄이상 존재시 나머지 해결(0≤N)
		int Y = read();				// 최소로 찾을 오류 개수 Y(0<=M)
		
		if(N == 1)
		{
			System.out.print(M - Y);
			return;
		}
		
		int s = 1;
		int e = 1;
		while(s != N)
		{
			// 결과를 반영할 조건에 해당되지 않고, e가 유효범위일때,
			if(!(X <= connect && Y<= errCnt) && e <= N)	// 고쳐야 되는 경우
			{
				if(v[e++])		// 고쳐야하는 곳이면
					errCnt++;	// 고친다
				connect++;		// 연결을 추가한다
			}
			else	// 결과를 반영할 조건이거나, e가 범위 벗어났으면,
			{
				// 결과를 반영할 조건이면 결과에 반영
				if(X <= connect && Y<= errCnt)
					ans = Math.min(ans, errCnt);
				// s위치가 에러였다면 errCnt원복
				if(v[s++])
					--errCnt;
				--connect;// s가 증가함에 따라 연결도 원복
			}
		}
		System.out.print(M - ans);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}

