//https://www.acmicpc.net/problem/23835
//1초 512MB
//5 // 방의 개수 N(1<=1,000)
//1 2 // 연결된 두방 번호
//2 3
//3 4
//2 5
//5 // 질의 수(1<=1,000)
//1 3 5 // 1로 시작하면 두 방 사이에 우유를 놓는다.
//1 4 5
//1 1 2
//2 2// 2로 시작하면 해당 노드에 지금까지 배달한 우유의 총 개수를 계산한다.
//2 5
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static int N, Q;
	static int s, e;
	static int milk[];
	static List<Integer> adList[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());// 방의 개수 N(1<=1,000)
		milk = new int[N + 1];
		adList = new ArrayList[N + 1];
		
		for(int i=0; i<=N; i++)
			adList[i] = new ArrayList<>();
		
		for(int i=1; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adList[a].add(b);
			adList[b].add(a);
		}
		
		Q = Integer.parseInt(br.readLine());// 질의 수(1<=1,000)
		
		StringBuilder sb = new StringBuilder();
		while(Q-->0)
		{
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			if(cmd == 2)
			{
				sb.append(milk[Integer.parseInt(st.nextToken())])
					.append('\n');
				continue;
			}
			
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			
			dfs(s, 0, 0);
		}
		
		System.out.print(sb);
	}
	static boolean dfs(int now, int parent, int depth) {
		if(now == e)
			return true;
		
		for(int next : adList[now])
		{
			if(next != parent)
			{
				milk[next] += depth + 1;
				
				if(dfs(next, now, depth + 1))
					return true;
				
				milk[next] -= depth + 1;
			}
		}
		return false;
	}
}