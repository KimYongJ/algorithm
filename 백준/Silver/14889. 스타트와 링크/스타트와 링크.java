// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, M, diff, map[][];
	static boolean visit[];
	
	// 값 연산을 빠르게 하기 위한 조치
	public static void init() 
	{
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++) 
			{
				map[i][j] += map[j][i];
				map[j][i] = map[i][j];
			}
	}
	public static void cal() {
		int sum1 = 0;
		int sum2 = 0;
		
		for(int i=0; i<N-1; i++) {
			for(int j=i+1; j<N; j++) {
				if( visit[i] && visit[j] )
					sum1 += map[i][j];
				else if( !visit[i] && !visit[j] )
					sum2 += map[i][j];
			}
		}
		int value = Math.abs(sum1-sum2)/2;
		if(value == 0) {
			System.out.print(0);
			System.exit(0);
		}
		diff = Math.min(diff, value);
	}
	public static void DFS(int depth, int i) {
		if(depth == M) // 절반을 뽑았을 때 
		{
			cal();
		}
		
		for(int idx = i; idx<N; idx++) 
		{
			visit[idx] = true;
			DFS(depth+1, idx+1);
			visit[idx] = false;
		}
	}
	public static void main(String[] args)throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N 		= Integer.parseInt(br.readLine());
		M		= N/2; // 한팀의 갯수
		diff	= Integer.MAX_VALUE;
		map 	= new int[N][N];
		visit 	= new boolean[N];
		for(int i=0; i<N; i++) 
		{
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		init(); // map배열 값 수정
		DFS(0,0);// N개 중 M개를 뽑는 것
		
		
		System.out.println(diff);
	}

	// 빠른 입력을 위한 함수
	static int read() throws Exception 
	{
	        int c, n = System.in.read() & 15;
	        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
	        return n;
	}
}