//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2842
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

class Node{
	int y, x;Node(int y,int x){this.y=y; this.x=x;}
}
class Main{
	static final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1},{-1,-1},{-1,1},{1,-1},{1,1}};
	static int s, e;
	static int arr[][][];
	static int H[];
	static boolean visit[][];
	public static void main(String[] args)throws Exception{
		
		final char	POSTOFFIC	= 'P';
		final char	HOUSE		= 'K';
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N			= Integer.parseInt(br.readLine());
		arr	= new int[2][N+2][N+2];		// [0]은 방문 해야하는 집의 위치, [1]은 고도를 나타냄
		int Kcnt		= 0;
		int sy 			= 0;
		int sx 			= 0;
		int min			= Integer.MAX_VALUE;
		
		for(int y=1; y<=N; y++)
		{
			String str = br.readLine();
			for(int x=1; x<=N; x++)
			{
				char c = str.charAt(x-1);
				if(c == HOUSE)
				{
					arr[0][y][x] = 1;
					Kcnt++;
				}
				else if(c == POSTOFFIC)
				{
					sy = y;
					sx = x;
				}
			}
		}
		HashSet<Integer> set = new HashSet<>();
		for(int y=1; y<=N; y++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int x=1; x<=N; x++)
			{
				set.add(arr[1][y][x] = Integer.parseInt(st.nextToken()));
				min = Math.min(min, arr[1][y][x]);
			}
		}

		H = new int[set.size()];
		int idx = 0;
		for(int s : set)
			H[idx++] = s;
			
		Arrays.sort(H);

		s = Arrays.binarySearch(H,  min);
		e = s;
		int MAXHEIGHT = H.length;
		int result = 1000001;
		while(s<=e && e < MAXHEIGHT)
		{
			visit = new boolean[N + 2][N + 2];
			if(H[s] <= arr[1][sy][sx] && arr[1][sy][sx] <= H[e] && DFS(sy, sx) == Kcnt)
			{
				result = Math.min(result, H[e] - H[s]);
				s++;
			}
			else
				e++;
		}
		System.out.print(result);
	}
	public static int DFS(int y, int x) {
		int cnt = 0;
		for(int xy[] : dxy)
		{
			int nextY = y + xy[0];
			int nextX = x + xy[1];
			int height= arr[1][nextY][nextX];
			if(height > 0 && !visit[nextY][nextX] && H[s] <= height && height<= H[e])
			{
				visit[nextY][nextX] = true;
				if(arr[0][nextY][nextX] == 1)
					cnt++;
				cnt += DFS(nextY, nextX);
			}
		}
		return cnt;
	}
}