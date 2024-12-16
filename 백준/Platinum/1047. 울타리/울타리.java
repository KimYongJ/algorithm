//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1047
//2초, 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Point{
	int x,y,len;
	Point(int x, int y, int len){this.x=x; this.y=y; this.len=len;}
}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int result	= 1<<30;
		int N		= Integer.parseInt(br.readLine());	// 2<=40
		Point[] arr = new Point[N];
		int x[]		= new int[N];
		int y[]		= new int[N];
		for(int i=0; i<N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			x[i]	= Integer.parseInt(st.nextToken());
			y[i]	= Integer.parseInt(st.nextToken());
			int l	= Integer.parseInt(st.nextToken());
			arr[i]	= new Point(x[i],y[i],l);
		}
		
		Arrays.sort(arr, (a,b)-> b.len - a.len);
		
		for(int x1=0; x1<N; x1++)
			for(int y1=0; y1<N; y1++)
				for(int x2=0; x2<N; x2++)
					if(x2 != x1)
						for(int y2=0; y2<N; y2++)
							if(y1!=y2)
							{
								boolean visit[] = new boolean[N];
								int maxX = Math.max(x[x1], x[x2]);
								int maxY = Math.max(y[y1], y[y2]);
								int minX = Math.min(x[x1], x[x2]);
								int minY = Math.min(y[y1], y[y2]);
								int squre = (maxX - minX + maxY - minY) << 1;
								int cnt	= 0;
								for(int i=0; i<N; i++)
								{
									// 범위 벗어나는 것은 잘라서 울타리로 만든다.
									if(maxX<arr[i].x || arr[i].x<minX || maxY<arr[i].y || arr[i].y<minY)
									{
										visit[i] = true;
										squre -= arr[i].len;
										++cnt;
									}
								}
								if(squre <= 0)
									result = Math.min(result, cnt);
								else
								{
									// 울타리를 다 두를 수 있을 때 까지 나무를 자른다. 울타리 범위 안에있는 나무를 자르는 것
									for(int i=0; i<N; i++)
									{
										if(!visit[i])
										{
											squre -= arr[i].len;
											++cnt;
											if(squre<=0)
											{
												result = Math.min(result, cnt);
												break;
											}
										}
									}
								}
							}
		
		if(result == 1<<30 || result == N)
			System.out.print(N - 1);
		else
			System.out.print(result);
	}
}
