// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N;
	static int MAX;			// 최대 승수
	static int rtable[][];	// 상성 표 
	static int action[][];	// 낼 순서
	static boolean visit[];	// 방문 체크 배열
	
	public static boolean cal() {
		int win[] = new int[3];
		int idx[] = new int[3];
		int p1 = 0, p2 = 1, winner;
		int max = 0; // 최대 승수
		while(max != MAX && idx[0] != N)
		{
			int a1 = action[p1][idx[p1]++];
			int a2 = action[p2][idx[p2]++];
			
			if(rtable[a1][a2] == 2)
				winner = p1;
			else if(rtable[a1][a2] == 0)
				winner = p2;
			else 
				winner = p1 < p2 ? p2 : p1;

			max = ++win[winner];
			p2	= 3 - p1 - p2;
			p1	= winner;
		}
		return win[0] == MAX;
	}
	public static boolean backtracking(int idx) {
		if(idx < 0)
			return cal();
		
		for(int i=0; i<N; i++) 
		{
			if(!visit[i]) 
			{
				visit[i] = true;
				action[0][idx] = i;
				if(backtracking(idx - 1))
					return true;
				visit[i] = false;
			}
		}
		return false;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer	st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());
		MAX		= Integer.parseInt(st.nextToken());
		visit	= new boolean[N];
		rtable	= new int[N][N];
		action	= new int[3][20];
		
		for(int y=0; y<N; y++) 
		{
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<N; x++) 
				rtable[y][x] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i<=2; i++) 
		{
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++)
				action[i][j] = Integer.parseInt(st.nextToken())-1;
		}
		
		System.out.print(backtracking(N-1) ? 1 : 0);
		
	}
}