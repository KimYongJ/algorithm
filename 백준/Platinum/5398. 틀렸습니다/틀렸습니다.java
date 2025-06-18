//https://www.acmicpc.net/problem/5398
//1초 256MB
//2// 테스트케이스 
//2 2// 가로 단어, 세로 단어 개수(1<=500)
//0 1 BAPC// 가로 단어 수 만큼 각 가로 단어의 시작 위치 x,y와 답이라 생각하는 단어가 대문자로(W) 주어짐(0 ≤ x, y ≤ 1,000 이고 1 ≤ Length(W) ≤ 1,000)
//0 2 LEIDEN
//0 0 SOLUTION// 세로 단어 수 만큼 시작 위치 x,y와 답이라 생각하는 단어가 대문자로 주어짐(0 ≤ x, y ≤ 1,000 이고 1 ≤ Length(W) ≤ 1,000)
//2 1 WINNER
//1 4
//0 1 HELLO
//1 0 HI
//2 0 BYE
//3 0 GOODBYE
//4 0 FAREWELL
////이하답
//3
//4
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static int match[];
	static int visitTime[];
	static int time;
	static List<Integer> adList[];
	static char[][] map;
	static int[][] xIdx;
	static int X, Y;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		init();
		
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			clear();
			
			st = new StringTokenizer(br.readLine());
			X = Integer.parseInt(st.nextToken());// 가로 단어(1<=500)
			Y = Integer.parseInt(st.nextToken());// 세로 단어 개수(1<=500)
			for(int i=0; i<X; i++)
			{
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				String word = st.nextToken();
				for(int j=0; j<word.length(); j++)
				{
					map[x + j][y] = word.charAt(j);
					xIdx[x + j][y] = i;
				}
			}
			for(int i=0; i<Y; i++)
			{
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				String word = st.nextToken();
				for(int j=0; j<word.length(); j++)
				{
					char c = word.charAt(j);
					if(map[x][y + j] != c && map[x][y + j] != 0)
						adList[xIdx[x][y + j]].add(i);
				}
			}
			
			int cnt = 0;
			for(int i=0; i<X; i++)
			{
				++time;
				if(dfs(i))
					++cnt;
			}
			
			sb.append(Y + X - cnt).append('\n');
			
		}
		System.out.print(sb);
	}
	static boolean dfs(int now) {
		for(int next : adList[now])
		{
			if(visitTime[next] == time)
				continue;
			
			visitTime[next] = time;
			
			if(match[next] == -1 || dfs(match[next]))
			{
				match[next] = now;
				return true;
			}
		}
		return false;
	}
	static void clear()
	{
		for(int i=0; i<match.length; i++)
		{
			match[i] = -1;
			adList[i].clear();
		}
		for(int y=0; y<=2000; y++)
			for(int x=0; x<=2000; x++)
				map[y][x] = 0;
	}
	static void init() {
		int len = 2001;
		match = new int[len];
		visitTime = new int[len];
		map = new char[len][len];
		adList = new ArrayList[len];
		xIdx = new int[len][len];
		for(int i=0; i<len; i++)
			adList[i] = new ArrayList<>();
	}
}