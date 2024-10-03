//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2842
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

class Node{
	int y, x;Node(int y,int x){this.y=y; this.x=x;}
}
class Main{
	
	public static void main(String[] args)throws Exception{
		final int	dxy[][]		= {{1,0},{0,1},{-1,0},{0,-1},{-1,-1},{-1,1},{1,-1},{1,1}};
		final char	POSTOFFIC	= 'P';
		final char	HOUSE		= 'K';
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N			= Integer.parseInt(br.readLine());
		int arr[][][]	= new int[2][N+2][N+2];		// [0]은 방문 해야하는 집의 위치, [1]은 고도를 나타냄
		int Kcnt = 0, sy = 0, sx = 0;
		
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
				set.add(arr[1][y][x] = Integer.parseInt(st.nextToken()));
		}

		int H[] = new int[set.size()];
		int idx = 0;
		for(int s : set)
			H[idx++] = s;
			
		Arrays.sort(H);

		int s = 0;
		int e = 0;
		int MAXHEIGHT = H.length;
		int result = 1000001;
		while(s<=e && e < MAXHEIGHT)
		{
			boolean flag = false;

			if(H[s] <= arr[1][sy][sx] && arr[1][sy][sx] <= H[e])
			{
				ArrayDeque<Node> q = new ArrayDeque<>();
				boolean visit[][] = new boolean[N+2][N+2];
				int cnt = 0;
				visit[sy][sx] = true;
				q.add(new Node(sy, sx));
				while(!q.isEmpty())
				{
					Node now = q.poll();
					for(int xy[] : dxy)
					{
						int nextY = now.y + xy[0];
						int nextX = now.x + xy[1];
						int height= arr[1][nextY][nextX];
						if(height > 0 && !visit[nextY][nextX] && H[s] <= height && height<= H[e])
						{
							visit[nextY][nextX] = true;
							q.add(new Node(nextY, nextX));
							if(arr[0][nextY][nextX] == 1)
								cnt++;
						}
					}
					if(cnt == Kcnt)
					{
						flag = true;
						break;
					}
				}
			}
			if(flag)
			{
				result = Math.min(result, H[e] - H[s]);
				s++;
			}
			else {
				e++;
			}
		}
		System.out.print(result);
	}
}