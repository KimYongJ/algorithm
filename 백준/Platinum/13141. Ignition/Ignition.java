//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/13141
class Main{
	
	public static void main(String[] args)throws Exception{
		final int MAX	= 1<<30;
		double MIN		= MAX;
		int N			= read();	// 2<=200
		int M			= read();	// N-1<=20000
		int[][]adNode	= new int[N+1][N+1];
		double[][]dist	= new double[N+1][N+1];
		
		// 플로이드워셜을 위한 기본 세팅
		for(int y=1; y<=N; y++)
			for(int x=1; x<=N; x++)
				if(y!=x)
					dist[y][x] = MAX;
		// 기본 입력 받음과 동시에 플로이드워셜 실행할 배열 값 세팅, 및 인접 노드까지의 거리 중 가장 큰 것을 담음(결과는 가장 긴시간이기 때문)
		for(int i=0; i<M; i++)
		{
			int a		= read();
			int b		= read();
			int l		= read();
			adNode[a][b]= Math.max(adNode[a][b], l);
			adNode[b][a]= Math.max(adNode[b][a], l);
			dist[a][b]	= Math.min(dist[a][b], l);
			dist[b][a]	= Math.min(dist[b][a], l);
		}
		// 플로이드워셜 실행
		for(int k=1; k<=N; k++)
			for(int i=1; i<=N; i++)
				for(int j=1; j<=N; j++)
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);

		// 최단거리 계산
		for(int start=1; start<=N; start++)
		{
			double maxBurnTime = 0;
			for(int nowNode=1; nowNode<=N; nowNode++)
			{
				for(int nextNode=1; nextNode<=N; nextNode++)
				{
					double max		= Math.max(dist[start][nowNode], dist[start][nextNode]);
					double min		= Math.min(dist[start][nowNode], dist[start][nextNode]);
					double burnTime = max + ((min + adNode[nowNode][nextNode] - max) / 2.0);
					maxBurnTime		= Math.max(maxBurnTime, burnTime);
				}
			}
			MIN = Math.min(MIN, maxBurnTime);
		}
		
		System.out.printf("%.1f",MIN);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}