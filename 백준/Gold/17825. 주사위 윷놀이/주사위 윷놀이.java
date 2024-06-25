// https://github.com/kimyongj/algorithm

class Main{
	
	static final int FIVE[]		= {5, 21, 22, 23, 29, 30, 31, 20, 32};	// 길이 9
	static final int FTEEN[]	= {15, 26, 27, 28, 29, 30, 31, 20, 32};	// 길이 9
	static final int TEN[]		= {10, 24, 25, 29, 30, 31, 20, 32, 32};	// 길이 9로 맞춤
	static final int score[]	= {0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 13, 16, 19, 22, 24, 28, 27, 26, 25, 30, 35, 0};
	static int order[]			= new int[10];		// 주사위 결과를 담을 배열
	static int horse[]			= new int[4];		// 말의 위치(인덱스)를 담을 배열
	static boolean visit[]		= new boolean[33];	// 말이 특정 위치에 있는지 체크하기 위한 배열
	static int result;
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static int getNextIndex(int startIdx, int cnt) {
		if(startIdx == 5) {
			return FIVE[cnt];
		}
		else if(startIdx == 10) {
			return TEN[cnt];
		}
		else if(startIdx == 15) {
			return FTEEN[cnt];
		}
		else if(startIdx == 20) {
			return 32;
		}
		else if(29<= startIdx) 
		{
			startIdx += cnt;
			if(startIdx  == 32) return 20;
			else if(startIdx> 32)return  32;
			else return startIdx;
		}
		else if(20 < startIdx) 
		{
			for(int i=1; i<9; i++)
			{
				if(FIVE[i] == startIdx) {
					return FIVE[i+cnt];
				}
				if(FTEEN[i] == startIdx) {
					return FTEEN[i+cnt];
				}
				if(TEN[i] == startIdx) {
					return TEN[i+cnt];
				}
			}
		}
		else {
			return startIdx + cnt > 20 ? 32 : startIdx + cnt;
		}
		return 0;
	}
	public static void backtracking(int depth,int sum) {
		if(depth == 10) 
		{
			result = Math.max(result, sum);
			return;
		}
		int nowIdx, nextIdx;
		for(int i=0; i<4; i++)
		{
			nowIdx	= horse[i];	// i번 말의 현재 인덱스를 가져옴
			
			if(nowIdx == 32)	// 끝에 도달한 말이면 스킵
				continue;
			
			nextIdx = getNextIndex(nowIdx, order[depth]);	// 시작 말의 위치에서 이동하는 칸수를 계산해 다음 인덱스를 가져옴
			if(nextIdx == 32 || !visit[nextIdx]) 			// 다음 인덱스가 끝이거나, 다음 인덱스가 방문하지 않은 곳이라면 
			{
				horse[i]		= nextIdx;	// 현재 말의 인덱스를 변경
				visit[nextIdx]	= true;		// 다음 말의 방문 체킹
				visit[nowIdx]	= false;	// 기존 자리 방문 해제
				if(nextIdx == 32)
					backtracking(depth + 1, sum); // 끝이면 더하지 않고 종료
				else
					backtracking(depth + 1, sum + score[nextIdx]);
				visit[nowIdx]	= true;		// 기존 자리 방문 체킹
				visit[nextIdx]	= false;	// 다음 장소 방문 해제
				horse[i]		= nowIdx;	// 말 인덱스 원상 복귀
			}
		}
		
	}
	public static void main(String[] args)throws Exception{
		for(int i=0; i<10; i++) 
		{
			order[i] = read();
		}
		
		backtracking(0,0);
		
		System.out.print(result);
	}
}