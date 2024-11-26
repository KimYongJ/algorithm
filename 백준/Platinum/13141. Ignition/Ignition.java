//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/13141
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static final int MAX = 1<<30;
	static int		N, M;
	static double[][]dist;
	static int[][]	adNode;
	static double	MIN = MAX;
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());	// 2<=200
		M		= Integer.parseInt(st.nextToken());	// N-1<=20000
		adNode	= new int[N+1][N+1];
		dist	= new double[N+1][N+1];

		// 플로이드워셜을 위한 기본 세팅
		for(int y=1; y<=N; y++)
			for(int x=1; x<=N; x++)
				if(y!=x)
					dist[y][x] = MAX;
		// 기본 입력 받음과 동시에 플로이드워셜 실행할 배열 값 세팅
		for(int i=0; i<M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a		= Integer.parseInt(st.nextToken());
			int b		= Integer.parseInt(st.nextToken());
			int l		= Integer.parseInt(st.nextToken());
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
}