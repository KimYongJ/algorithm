//https://www.acmicpc.net/problem/13503
//2초 512MB
//7 11// 정점 개수 (1<=10,000), 간선 개수(0<=100,000)
//1 2// u, v가 주어지며 u에서 v로 가는 간선이라는 의미
//1 5
//2 5
//2 3
//2 7
//3 4
//3 6
//4 6
//5 4
//5 6
//7 3
//그래프의 모든 정점을 포함시키기 위해 필요한 체인 개수의 최솟값 : 2
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{

	static int N, M;
	static int time;
	static int match[];
	static int visitTime[];
	static List<Integer> adList[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 정점 개수 (1<=10,000)
		M = Integer.parseInt(st.nextToken());// 간선 개수(0<=100,000)
		match = new int[N + 1];
		visitTime = new int[N + 1];
		adList = new ArrayList[N + 1];
		
		for(int i=0; i<=N; i++)
			adList[i] = new ArrayList<>();
		
		for(int i=0; i<M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adList[u].add(v);
		}
		
		int cnt = 0;
		for(int i=1; i<=N; i++)
		{
			++time;
			if(dfs(i))
				++cnt;
		}
		System.out.print(N - cnt);
	}
	static boolean dfs(int now)
	{
		for(int next : adList[now])
		{
			if(visitTime[next] == time)
				continue;
			
			visitTime[next] = time;
			if(match[next] == 0 || dfs(match[next]))
			{
				match[next] = now;
				return true;
			}
		}
		return false;
	}
}