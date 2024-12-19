//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/31794
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Target{
	int origin, energy;
	Target(int o){origin=energy=o;}
}

class Main{
	
	static final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int N, K, max;
	static Target map[][];
	static int attack[];

	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N		= Integer.parseInt(br.readLine());	// 보드판크기 (2<=8)
		K		= Integer.parseInt(br.readLine());	// 사격 횟수 (1<=5)
		map		= new Target[N+2][N+2];
		attack	= new int[K];
		
		for(int y=1; y<=N; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=N; x++)
				map[y][x] = new Target(Integer.parseInt(st.nextToken()));
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<K; i++)
			attack[i] = Integer.parseInt(st.nextToken());
		
		back(0, 0);
		
		System.out.print(max);
	}
	public static void back(int depth, int score) {
		if(depth == K)
		{
			max = Math.max(max, score);
			return;
		}
		
		for(int y=1; y<=N; y++)
			for(int x=1; x<=N; x++)
				if(0 < map[y][x].energy)
				{
					// 보너스인 경우
					if(10 <= map[y][x].energy)
					{
						int energy = map[y][x].energy;
						map[y][x].energy = 0;
						back(depth + 1, score + map[y][x].origin);
						map[y][x].energy = energy;
					}
					// 공격해서 체력이 0 이 된 경우
					else if(map[y][x].energy <= attack[depth])
					{
						Target base[] = new Target[4];		// 상하좌우 원본 저장할 것
						int origin = map[y][x].origin;
						int energy = map[y][x].energy;
						int div		= origin / 4;
						if(0< div)
						{
							for(int i=0; i<4; i++)
							{
								int ny = y + dxy[i][0];
								int nx = x + dxy[i][1];
								if(map[ny][nx] != null && map[ny][nx].energy == 0)
								{
									base[i] = map[ny][nx];
									map[ny][nx] = new Target(div);
								}
							}
							map[y][x].energy = 0;
							back(depth + 1, score + origin);
							map[y][x].energy = energy;
							for(int i=0; i<4; i++)
							{
								int ny = y + dxy[i][0];
								int nx = x + dxy[i][1];
								if(base[i] != null)
									map[ny][nx] = base[i];
							}
						}
						else
						{
							map[y][x].energy = 0;
							back(depth + 1, score + origin);
							map[y][x].energy = energy;
						}
					}
					// 공격했지만 체력이 남은 경우
					else
					{
						map[y][x].energy -= attack[depth];
						back(depth + 1, score);
						map[y][x].energy += attack[depth];
					}
					break;
				}
	}

}