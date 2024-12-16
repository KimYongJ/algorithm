//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1047
//2초, 128MB
import java.util.Arrays;

class Point{
	int x,y,len;
	Point(int x, int y, int len){this.x=x; this.y=y; this.len=len;}
}

class Main{
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();	// 2<=40
		int result	= N-1;		// 최악의 경우 모든 나무를 자른다.
		Point[] arr = new Point[N];
		int x[]		= new int[N];
		int y[]		= new int[N];
		
		for(int i=0; i<N; i++)
		{
			x[i]	= read();
			y[i]	= read();
			arr[i]	= new Point(x[i], y[i], read());
		}
		
		Arrays.sort(arr, (a,b)-> b.len - a.len);
		Arrays.sort(x);
		Arrays.sort(y);
		for(int x1=0; x1<N-1; x1++)
			for(int y1=0; y1<N-1; y1++)
				for(int x2=x1+1; x2<N; x2++)
					for(int y2=y1+1; y2<N; y2++)
					{
						boolean visit[] = new boolean[N];
						int maxX	= x[x2];
						int maxY	= y[y2];
						int minX	= x[x1];
						int minY	= y[y1];
						int squre	= (maxX - minX + maxY - minY) << 1;
						int cnt		= 0;
						for(int i=0; i<N; i++)
						{
							// 범위 벗어나는 것은 잘라서 울타리로 만든다.
							if(maxX<arr[i].x || arr[i].x<minX || maxY<arr[i].y || arr[i].y<minY)
							{
								visit[i] = true;
								squre -= arr[i].len;
								if(result < ++cnt)
									break;
							}
						}
						// 범위 벗어나는것만 자르면 울타리를 두를 수 있을 때 바로 result에 결과 대입
						if(squre <= 0)
							result = Math.min(result, cnt);
						else
						{
							// 울타리를 다 두를 수 있을 때 까지 나무를 자른다. 울타리 범위 안에있는 나무를 자르는 것
							for(int i=0; i<N; i++)
								if(!visit[i])
								{
									if(result < ++cnt)
										break;
									squre -= arr[i].len;
									if(squre<=0)
									{
										result = Math.min(result, cnt);
										break;
									}
								}
						}
					}
		
		System.out.print(result);
	}
}