//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/2615
// 검은색 이긴 경우 1, 아니면 2, 무승부 0
// 둘째 줄에 해당라인의 가장 왼쪽위 출력
class Main{
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		final int dxy[][]	= {{1,0},{1,1},{0,1},{-1,1}};// 아래, 오른쪽아래대각선, 오른쪽, 오른쪽위 대각선
		final int rxy[][]	= {{-1,0},{-1,-1},{0,-1},{1,-1}};
		int N				= 19;
		int map[][]			= new int[N + 2][N + 2];

		for(int y=1; y<=N; y++)
			for(int x=1; x<=N; x++)
				map[y][x] = read();
		
		for(int y=1; y<=N; y++)
			for(int x=1; x<=N; x++)
				if(0<map[y][x])
				{
					int flag = map[y][x];
					for(int i=0; i<4; i++)
					{
						int cnt = 1;						
						int nextY = y;
						int nextX = x;
						
						while(map[nextY + dxy[i][0]][nextX + dxy[i][1]] == flag)
						{
							++cnt;
							nextY += dxy[i][0];
							nextX += dxy[i][1];
						}

						if(cnt == 5 && map[y + rxy[i][0]][x + rxy[i][1]] != flag)
						{

							
							StringBuilder sb = new StringBuilder();
							sb.append(flag).append('\n');
							sb.append(y).append(' ').append(x);
							System.out.print(sb);
							return;
						}
					}
				}

		System.out.print(0);
		
	}
}