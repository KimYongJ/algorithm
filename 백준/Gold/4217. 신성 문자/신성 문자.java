//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/4217
// 1) 0인 곳에 대해 특정 숫자로 마킹한다.
// 2) 숫자가 1인 곳을 BFS로 돌면서 1이 아닌 숫자를 몇개나 만나는지 찾는다.
// 3) 해당 숫자 만큼 알파벳을 결과에 추가한다.
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
class Main{
	
	static final String[] BINARY = {"0000","0001","0010","0011","0100","0101","0110","0111","1000","1001","1010","1011","1100","1101","1110","1111"};
	static final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static char KEY[] = {'W','A','K','J','S','D'};
	static int MARKING;
	static int Y, X, map[][];
	static boolean meetNum[];
	static ArrayDeque<int[]> q;
	static ArrayList<ArrayList<Character>> result = new ArrayList<>();
	
	public static void FILL_BFS(int y, int x) {
		q			= new ArrayDeque<>();
		map[y][x]	= MARKING;
		
		q.add(new int[] {y, x});
		
		while(!q.isEmpty())
		{
			int[] now = q.poll();
			
			for(int xy[] : dxy)
			{
				int nextY = now[0] + xy[0];
				int nextX = now[1] + xy[1];
				if(0<=nextY && 0<=nextX && nextY<Y+2 && nextX<X+2 && 
					map[nextY][nextX] == 0)
				{
					map[nextY][nextX] = MARKING;
					q.add(new int[] {nextY, nextX});
				}
			}
		}
		++MARKING;
	}
	public static int CHECK_BFS(int y, int x) {
		int cnt		= 0;
		meetNum		= new boolean[MARKING];
		q			= new ArrayDeque<>();
		map[y][x]	= 0;
		
		q.add(new int[] {y,x});
		
		while(!q.isEmpty())
		{
			int[] now = q.poll();

			for(int xy[] : dxy)
			{
				int nextY = now[0] + xy[0];
				int nextX = now[1] + xy[1];
				if(map[nextY][nextX] == 1)
				{
					map[nextY][nextX] = 0;
					q.add(new int[] {nextY, nextX});
				}
				else if(1 < map[nextY][nextX] && !meetNum[map[nextY][nextX]])
				{
					++cnt;
					meetNum[map[nextY][nextX]] = true;
				}
			}
		}
		return cnt - 1;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			MARKING = 2;
			Y		= Integer.parseInt(st.nextToken());	// 0<=200
			X		= Integer.parseInt(st.nextToken());	// 0<50
			map		= new int[Y+2][(X*4)+2];
			
			if(Y==0 || X==0)
				break;
			
			for(int y=1; y<=Y; y++)
			{
				String str = br.readLine();
				int idx = 1;
				for(int x=1; x<=X; x++)
				{
					char c = str.charAt(x-1);
					int num = c > '9' ? c - 'a' + 10 : c - '0';
					for(int b : BINARY[num].toCharArray())
						map[y][idx++] = b-'0';
				}
			}
			
			X = (X*4);
			
			// map에서 0인 곳에 MARKING_NUM을 칠한다.
			for(int y=1; y<=Y; y++)
				for(int x=1; x<=X; x++)
					if(map[y][x] == 0)
						FILL_BFS(y, x);	
			
			// map에서 1인 곳을 돌며 1초과인 숫자를 몇개나 만나는지 체크 한다.
			ArrayList<Character> res = new ArrayList<>();
			for(int y=1; y<=Y; y++)
				for(int x=1; x<=X; x++)
					if(map[y][x] == 1)
					{
						int cnt = CHECK_BFS(y, x);
						res.add(KEY[cnt]);
					}

			result.add(res);
		}
		
		// 결과 출력
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<result.size(); i++)
		{
			Collections.sort(result.get(i));
			sb.append("Case ").append(i+1).append(": ");
			for(char c : result.get(i))
				sb.append(c);
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}