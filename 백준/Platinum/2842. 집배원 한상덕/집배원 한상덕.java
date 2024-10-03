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
	static int arr[][];
	static int H[];
	static boolean isHouse[][];
	static boolean visit[][];
	public static void main(String[] args)throws Exception{
		
		final char	POSTOFFIC	= 'P';
		final char	HOUSE		= 'K';
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N		= Integer.parseInt(br.readLine());
		int Kcnt	= 0;
		int sy 		= 0;
		int sx 		= 0;
		arr			= new int[N+2][N+2];
		isHouse		= new boolean[N+2][N+2];
		
		for(int y=1; y<=N; y++)
		{
			String str = br.readLine();
			for(int x=1; x<=N; x++)
			{
				char c = str.charAt(x-1);
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
		}
		HashSet<Integer> set = new HashSet<>();
		for(int y=1; y<=N; y++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int x=1; x<=N; x++)
				set.add(arr[y][x] = Integer.parseInt(st.nextToken()));
		}

		H = new int[set.size()];
		int idx = 0;
		for(int s : set)
			H[idx++] = s;
			
		Arrays.sort(H);

		s = 0;
		e = Arrays.binarySearch(H,  arr[sy][sx]);
		
		int MAXHEIGHT = H.length;
		int result = 1000001;
		while(s<=e && e < MAXHEIGHT && H[s] <= arr[sy][sx] && arr[sy][sx] <= H[e])
		{
			visit = new boolean[N + 2][N + 2];
			if(DFS(sy, sx) == Kcnt)
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
}