//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2842
import java.util.Arrays;
import java.util.HashSet;
class Main{
	
	static final char	POSTOFFIC	= 'P';
	static final char	HOUSE		= 'K';
	static final int	dxy[][]		= {{1,0},{0,1},{-1,0},{0,-1},{-1,-1},{-1,1},{1,-1},{1,1}};
	static int			s, e;
	static int			arr[][];
	static int			H[];
	static boolean		isHouse[][];
	static boolean		visit[][];
	
	public static int DFS(int y, int x)
	{
		int cnt = 0;
		for(int xy[] : dxy)
		{
			int nextY 	= y + xy[0];
			int nextX 	= x + xy[1];
			int height	= arr[nextY][nextX];
			
			if(height > 0 && !visit[nextY][nextX] && H[s] <= height && height<= H[e])
			{
				visit[nextY][nextX] = true;
				if(isHouse[nextY][nextX])
					cnt++;
				cnt += DFS(nextY, nextX);
			}
		}
		return cnt;
	}
	
	public static void main(String[] args)throws Exception{
		Reader in 	= new Reader();
		int N		= in.nextInt();				// 맵크기
		int Kcnt	= 0;						// 방문해야할 집들의 수
		int sy 		= 0;						// 우체국의 시작 위치
		int sx 		= 0;						// 우체국의 시작 위치
		arr			= new int[N+2][N+2];		// 높이를 담을 배열(패딩을 넣어 불필요한 좌표 유효성검사를 없앤다)
		isHouse		= new boolean[N+2][N+2];	// 해당 위치가 집인지 아닌지 판단할 배열(패딩을 넣어 불필요한 좌표 유효성검사를 없앤다)
		
		for(int y=1; y<=N; y++)
			for(int x=1; x<=N; x++)
			{
				char c = in.nextChar();
				if(c == HOUSE)
				{
					isHouse[y][x] = true;
					Kcnt++;
				}
				else if(c == POSTOFFIC)
				{
					sy = y;
					sx = x;
				}
			}

		HashSet<Integer> set = new HashSet<>();
		for(int y=1; y<=N; y++)
			for(int x=1; x<=N; x++)
				set.add(arr[y][x] = in.nextInt());

		// 높이를 중복없이 일차원 배열로 변경한다.
		int idx = 0;
		H = new int[set.size()];
		for(int s : set)
			H[idx++] = s;
		// 투포인터 탐색을 위해 높이를 오름차순으로 정렬한다.
		Arrays.sort(H);
		
		s = 0;										// 시작지점은 H의 0번째 인덱스
		e = Arrays.binarySearch(H,  arr[sy][sx]);	// 종료지점은 처음부터 H배열안에 있는 우체국의 시작 고도로 시작한다.
		
		int MAXHEIGHT = H.length;					// H의 인덱스를 못벗어 나게 한다.
		int result = 1000001;						// 최종결과를 담을 배열
		// H의 시작인덱스(s)는 H의 종료인덱스(e)보다 작아야하고, 또한 H[s]와 H[e]의 고도는 시작 고도를 벗어나서는 안된다.
		while(s<=e && e < MAXHEIGHT && H[s] <= arr[sy][sx] && arr[sy][sx] <= H[e])
		{
			visit = new boolean[N + 2][N + 2];

			if(DFS(sy, sx) == Kcnt)	// 해당 집들을 모두 방문할 수 있다면 H의 시작인덱스(s)를 추가해서 최소의 범위를 구한다.
			{
				result = Math.min(result, H[e] - H[s]);
				s++;
			}
			else			// 해당 집을 모두 방문할 수 없다면 동료인덱스(e)를 늘려 더 넓은 범위의 고도를 탐색하도록 한다.
				e++;
		}
		System.out.print(result);
	}
}




class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;
    char nextChar() throws Exception {
        byte c;
        while ((c = read()) < 32); // SPACE 분리라면 <=로, SPACE 무시라면 <로
        return (char)c;
    }
    
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
