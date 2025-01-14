//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/4902
//1초 256MB / test case
//5 -100 -100 -100 -100 -100 -100 -100 -100 -100 -100 -100 -100 100 100 100 -100 -100 -100 -100 -100 -100 100 -100 -100 -100
//0
//답 : 1. 400

class Main{
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int t = 0;
		while(true)
		{
			int Y	= read();
			int X	= (Y-1)*2 + 1 + 1;	// 누적합을 위해 x좌표는 필요한 양의 +1을 추가
			int max	= -1000;
			int[][] map	= new int[Y+1][X];
			
			if(Y == 0)
				break;
			
			for(int y=1; y<=Y; y++)
			{
				int len = (y-1)*2 + 1;
				for(int x=1; x<=len; x++)
				{
					// 절대값 |천|의 값이 들어옴, 입력과 동시에 행별로 누적합 계산
					int n = read();
					map[y][x] = n + map[y][x-1];
					max = Math.max(max, n);
				}
			}
			//정방향 y,x를 꼭지점으로 하는 삼각형
			for(int y=1; y<=Y; y++)
			{
				int len = (y-1)*2 + 1;
				for(int x=1; x<=len; x+=2)
				{
					int sum = 0;
					for(int plus = 0,y1=y; y1<=Y; plus +=2, y1++)
					{
						sum += map[y1][x+plus] - map[y1][x - 1];
						max = Math.max(max, sum);
					}
				}
			}
			//역방향 y,x를 꼭지점으로 하는 삼각형
			for(int y=Y; y>=1; y--)
			{
				int len = (y-1)*2 + 1;
				for(int x=2; x<=len; x+=2)
				{
					int sum = 0;
					for(int plus = 0,y1=y;1<=y1 ; plus +=2, y1--)
						// y1가 범위 안에있고, x가 해당y좌표의 최대를 초과하지 않으며, 역방향일 때 가장 오른쪽값(x-plus-1)이 양수일 때
						if(x<=(y1-1)*2 && 0 <= x - plus - 1)
						{
							sum += map[y1][x] - map[y1][x - plus - 1];
							max = Math.max(max, sum);
						}
				}
			}
			
			sb.append(++t).append(". ").append(max).append('\n');
		}
		System.out.print(sb);
	}
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) {
        n = (n << 3) + (n << 1) + (c & 15);}
        return m ? ~n + 1 : n;
    }
}