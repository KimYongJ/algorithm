//https://www.acmicpc.net/problem/17402
//1초 512MB
//서 입력되는 경우는 없다.첫 번째 줄에 철수와 영희가 합쳐서 총 몇 번의 선택을 하게 되는지 출력한다.
//4 4 6// N(1<=200), M(1<=200), X 표시된 칸의 수 K(1<=N*M)
//1 1//K줄에 걸쳐 두 자연수 X가 있는 좌표 X(1 ≤ N),Y(1 ≤ M)가 주어짐( 좌표 중복은 없음 )
//1 3
//2 4
//3 2
//3 4
//4 3
////총 몇번의 선택이 가능한지 : 4
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static int N, M, K;
	static List<Integer> adList[];
	
	static int time;
	static int match[];
	static int visitTime[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// N(1<=200)
		M = Integer.parseInt(st.nextToken());// M(1<=200)
		K = Integer.parseInt(st.nextToken());// X 표시된 칸의 수 K(1<=N*M)
		adList = new ArrayList[N + 1];
		match = new int[M + 1];
		visitTime = new int[M + 1];
		
		for(int i=0; i<=N; i++)
			adList[i] = new ArrayList<>();
		
		for(int i=0; i<K; i++)
		{
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			adList[x].add(y);
		}
		
		int cnt = 0;
		
		for(int x=1; x<=N; x++)
		{
			++time;
			if(dfs(x))
				++cnt;
		}
		System.out.print(N + M - cnt);
	}
	static boolean dfs(int x)
	{
		for(int y : adList[x])
		{
			if(visitTime[y] == time)
				continue;
			
			visitTime[y] = time;
			
			if(match[y] == 0 || dfs(match[y]))
			{
				match[y] = x;
				return true;
			}
		}
		return false;
	}
}