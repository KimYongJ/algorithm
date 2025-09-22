//https://www.acmicpc.net/problem/17822
//1초 512MB
//4 4 1// 원판의 수(2<=50), 원판에 적힌 숫자 개수(2<=50), 회전횟수(1<=50)
//1 1 2 3 // 원판 수만큼 적힌 수가 주어짐(1<=1000)
//5 2 4 2
//3 1 3 5
//2 1 3 2
//2 0 1 // 회전 횟수만큼 주어지며 xi, di, ki가 주어짐
//답 : 30
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static boolean visit[][];
	static boolean find;
	static int map[][];
	static int N, M, T;
	static int x, d, k;
	static int offset;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 원판의 수(2<=50)
		M = Integer.parseInt(st.nextToken());// 원판에 적힌 숫자 개수(2<=50)
		T = Integer.parseInt(st.nextToken());// 회전횟수(1<=50)
		map = new int[N][M];
		visit = new boolean[N][M];
		
		for(int i=0; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		while(T-->0)
		{
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken()); // 배수
			d = Integer.parseInt(st.nextToken()); // 방향 0은 시계, 1은 반시계
			k = Integer.parseInt(st.nextToken()) % M; // 회전 칸수
			offset = 0;
			if(d == 1){
				k = -k;// 반시계면 k는 음수
				offset = M;// 반시계면 offset은 M
			}
			
			clear();
			rotate();
			if(!deleteSame())
				calAverage();
		}
		
		int sum = 0;
		
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++)
				sum += map[i][j];
		
		System.out.print(sum);
	}
	static void clear() {
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++)
				visit[i][j] = false;
	}
	static void rotate() {
		for(int i=x; i<=N; i+=x)
		{
			int newArr[] = new int[M];
			
			for(int j=0; j<M; j++)
				newArr[(j + k + offset) % M] = map[i - 1][j];
			
			map[i - 1] = newArr;
		}
	}
	static boolean deleteSame() {
		find = false;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 0 || visit[i][j])
					continue;
				
				visit[i][j] = true;
				
				if(dfs(i, j, map[i][j], true))
					find = true;
			}
		}
		
		return find;
	}
	static boolean dfs(int i, int j, int target, boolean isFirst) {
		boolean same = !isFirst && map[i][j] == target;
		
		for(int xy[] : dxy)
		{
			int ni = i + xy[0];
			int nj = (j + xy[1] + M) % M;
			
			if(ni < 0 || N <= ni || visit[ni][nj] || map[ni][nj] != target)
				continue;
			
			visit[ni][nj] = true;
			same = true;
			
			dfs(ni, nj, target, false);
		}
		
		if(same)
			map[i][j] = 0;
		
		return same;
	}

	static void calAverage() {
		int sum = 0;
		int cnt = 0;
		
		for(int i=0; i<N; i++)
		{
			for(int j=0; j<M; j++)
			{
				if(map[i][j] == 0)
					continue;
				
				sum += map[i][j];
				cnt += 1;
			}
		}
		
		if(cnt == 0)return;
		
		double avg = (double)sum / cnt;
		
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++)
				if(map[i][j] == 0) continue;
				else if(map[i][j] > avg)map[i][j]--;
				else if(map[i][j] < avg)map[i][j]++;
	}
}