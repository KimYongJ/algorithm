//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2873
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void input(StringBuilder sb, int X, int Y,char mm[], char dir, int flag) {
		for(int x=0; x<X; x++)
		{
			for(int y=1; y<Y; y++)
				sb.append(mm[flag]);
			sb.append(dir);
			flag = (flag+1) % 2;
		}
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		final char ud[] = {'U','D'};
		final char lr[] = {'L','R'};
		final String RU = "RU";
		final String RD = "RD";
		int Y = read();
		int X = read();
		
		if(Y % 2 == 1 || X % 2 == 1)			// 하나라도 홀 수 일 경우 그냥 탐색하면됨
		{
			if(Y % 2 == 0)
				input(sb, X, Y, ud, 'R', 1);	// 다운부터 시작 W모양
			else
				input(sb, Y, X, lr, 'D', 1);	// 오른쪽 부터 시작 ㄹ모양
		}
		else									// 둘다 짝수일 경우
		{
			int my = 0, mx = 0, mn = 1001;
			for(int y=0; y<Y; y++)
			{
				for(int x=0; x<X; x++)
				{
					int n = read();
					if((y+x)%2 == 1 && mn > n)	// 두 좌표를 더했을 때 홀 수에 있는 위치의 최솟값을 찾음
					{
						mn = n;
						my = y;
						mx = x;
					}
				}
			}
			
			int sy = 0;							// 세로 행 인덱스
			int lrFlag = 1; 					// 왼쪽 오른쪽 플레그
			while(sy<Y)							// 세로는 2라인씩 탐색한다. (0,1) , (2,3) , (4,5) 씩..
			{
				if(sy == my || sy + 1 == my)	// 해당 구간에 최소 값이 있는지 체크
				{
					StringBuilder part = new StringBuilder();
					part.append('D');
					boolean flag = true;
					int repeat = X-2;
					while(repeat-->0)			// 규칙 : 처음 D를 넣고 X-2번씩 RU, RD를 반복하면됨
					{
						if(flag) part.append(RU);
						else part.append(RD);
						flag = !flag;
					}
					if(mx == 0)					// 최소값이 0번째 인덱스에 있을 때
						part.insert(0, 'R');
					else if(mx == X-1)			// 최소값이 맨마지막 인덱스에 있을 때
						part.append('R');
					else						// 최소값이 중간에 있을 때
						part.insert(mx*2, 'R');
					
					sb.append(part.toString()).append('D');
					lrFlag = 0; 				// 다음엔 L을 먼저 탐색토록함
				}
				else							// 최소값이 없다면 ㄹ모양으로 탐색
					input(sb, 2, X, lr, 'D', lrFlag);
				
				sy+=2;							// 두라인씩 탐색
			}
		}
		
		sb.deleteCharAt(sb.length()-1);
		
		System.out.print(sb.toString());
	}
}