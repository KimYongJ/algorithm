// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{

	static int	map[][] = new int[3][3];
	
	public static boolean check(int player) {
		for(int i=0; i<3; i++) 
		{
			if(map[i][0] == player && map[i][0] == map[i][1] && map[i][1] == map[i][2]) return true; // 가로 확인 
			if(map[0][i] == player && map[0][i] == map[1][i] && map[1][i] == map[2][i]) return true; // 세로 확인
		}
		if(map[0][0] == player && map[0][0] == map[1][1] && map[1][1] == map[2][2]) return true; // 대각선 확인
		if(map[0][2] == player && map[0][2] == map[1][1] && map[1][1] == map[2][0]) return true; // 대각선 확인

		return false;
	}
	
	// dfs는 인자로 전달된 player의 결과가 이겼는지 졌는지를 반환 한다. 비기면 0, 이기면 1, 졌으면 -1
	static int dfs(int player) {
		int nextPlayer = 3-player;
		if(check(nextPlayer)) // 상대가 이겼는지 체크
			return -1; // 상대가 이겼다면 더 이상 둘 필요가 없으므로 종료
		
		int min = 2;
		for(int y=0; y<3; y++) 
		{
			for(int x=0; x<3; x++) 
			{
				if(map[y][x] == 0) 
				{
					map[y][x] = player;
					min = Math.min(min,dfs(nextPlayer)); // 만약 dfs의 결과로 1이 반환되었다면, nextPlayer가 진것이고, 내가 이긴것이다.( 1이 반환되게 하기 위해 가장 마지막에 -min을 해준것) 
					map[y][x] = 0;
				}
			}
		}
		
		if(min == 2 || min == 0) // min이 변함이 없거나, 비긴것이면 0 반환
			return 0;
		
		return -min;	// 반환 하는 것은, 상대의 결과이기 때문에, 상대가 이긴거면 내가 진것이고, 내가 진것이면 상대가 이긴것이다. 그렇기에 부호를 반대로하여 결과를 반전시키고있다. 그래야 dfs를 호출한 dfs가 이긴건지 진건지 알 수 있음
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int flag = 0;	// 기준이 되는 플레이어를 가리기 위한 플레그
		for(int y=0; y<3; y++) 
		{
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<3; x++) 
			{
				map[y][x] = Integer.parseInt(st.nextToken());
				
				if(map[y][x] == 1) 			flag++;
				else if(map[y][x] == 2) 	flag--;
			}
		}
			
		int result = dfs(flag > 0 ? 2 : 1);

		char c = 'L';
		if(result == 0)
			c = 'D';
		else if(result == 1)
			c = 'W';
		
		System.out.print(c);
	}
}