// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main{
	
	static int 		MAX;
	static int		len;
	static int 		sy, sx;
	static int 		N, M, H;
	static int		dist[][];
	static boolean 	visit[];
	static ArrayList<int[]> position = new ArrayList<>();

	public static void backtracking(int beforeIdx, int cnt, int energy) {
		if(MAX < cnt && dist[0][beforeIdx] <= energy)	
			MAX = cnt;

		for(int i=1; i<len; i++)
			if(!visit[i]) 
			{
				visit[i] = true;
				if(dist[beforeIdx][i] <= energy)
					backtracking(i, cnt + 1, energy - dist[beforeIdx][i] + H);
				visit[i] = false;
			}
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken()); // 마을크기
		M 		= Integer.parseInt(st.nextToken()); // 초기체력
		H		= Integer.parseInt(st.nextToken()); // 회복양
		
		for(int y=1; y<=N; y++) 
		{
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=N; x++) 
			{
				int n = Integer.parseInt(st.nextToken());
				if(n == 1) 
				{
					sy = y;
					sx = x;
				}else if(n == 2) 
					position.add(new int[] {y,x});
			}
		}
		
		position.add(0,new int[] {sy,sx});
		len		= position.size();
		visit 	= new boolean[len];
		dist	= new int[len][len];
		// 거리 세팅
		for(int i=0; i<len-1; i++)
			for(int j=i+1; j<len; j++) 
			{
				int start[] = position.get(i);
				int now[]	= position.get(j);
				int d		= Math.abs(start[0]-now[0]) + Math.abs(start[1]-now[1]);
				dist[i][j] = dist[j][i] = d;
			}
		
		backtracking(0,0,M);
		
		System.out.print(MAX);
	}
}