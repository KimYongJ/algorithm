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
	static boolean 	visit[];
	static ArrayList<int[]> position = new ArrayList<>();
	
	public static boolean check(int beforeIdx, int nowIdx, int energy) {
		int start[] = position.get(beforeIdx);
		int now[]	= position.get(nowIdx);
		return (Math.abs(start[0]-now[0]) + Math.abs(start[1]-now[1])) <= energy;
	}
	
	public static void backtracking(int beforeIdx, int cnt, int energy) {
		if(MAX < cnt && check(0,beforeIdx,energy))	
			MAX = cnt;

		for(int i=1; i<len; i++)
			if(!visit[i]) 
			{
				visit[i] = true;
				int start[] = position.get(beforeIdx);
				int now[]	= position.get(i);
				int dist 	= Math.abs(start[0]-now[0]) + Math.abs(start[1]-now[1]);
				if(dist <= energy) {
					backtracking(i, cnt + 1, energy - dist + H);
				}
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
		
		backtracking(0,0,M);
		
		System.out.print(MAX);
	}
}