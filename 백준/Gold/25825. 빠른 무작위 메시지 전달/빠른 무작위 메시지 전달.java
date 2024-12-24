//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/25825
class Main{
	
	static int group[][] = {{0,1},{2,3},{4,5},{6,7},{8,9},{10,11}};
	static int map[][];
	static boolean visit[];
	static int MIN = 1<<30;
	
	public static void main(String[] args)throws Exception{
		map		= new int[12][12];
		visit	= new boolean[12];
		
		for(int y=0; y<12; y++)
			for(int x=0; x<12; x++)
				map[y][x] = read();
		
		for(int i=0; i<6; i++)
			for(int j=0; j<=1; j++)
			{
				visit[group[i][j]] = true;
				back(0, 0, i, group[i][j]);
				visit[group[i][j]] = false;
			}
		
		System.out.print(MIN);
	}
	public static void back(int depth, int time, int groupIdx, int prev) {
		if(depth == 11)
		{
			MIN = Math.min(MIN, time);
			return;
		}
		// 같은 그룹이 방문하지 않았을 때
		if(!visit[group[groupIdx][0]] || !visit[group[groupIdx][1]])
		{
			int idx = visit[group[groupIdx][0]] ? 1 : 0;
			visit[group[groupIdx][idx]] = true;
			back(depth + 1, time + map[prev][group[groupIdx][idx]], groupIdx, group[groupIdx][idx]);
			visit[group[groupIdx][idx]] = false;
		}
		// 같은 그룹이 다 방문했다면 다른 그룹을 찾는다.
		else
		{
			for(int i=0; i<6; i++)
				for(int j=0; j<=1; j++)
					if(!visit[group[i][j]])
					{
						visit[group[i][j]] = true;
						back(depth + 1, time + map[prev][group[i][j]], i, group[i][j]);
						visit[group[i][j]] = false;
					}
		}
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}