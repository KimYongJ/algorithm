//https://www.acmicpc.net/problem/24545
//1.5초 1024MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static int N;
	static int size;
	static int idx;
	static int maxDepth;
	static int max[];
	static boolean visit[];
	
	static List<Integer> adList[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		adList = new ArrayList[N + 1];
		max = new int[2];
		visit = new boolean[N + 1];
		
		for(int i=0; i<=N; i++)
			adList[i] = new ArrayList<>();
		
		for(int i=1; i<N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adList[a].add(b);
			adList[b].add(a);
		}
		
		dfs1(1, -1, 0);// 트리 지름의 양끝 노드를 구하기 위한 첫번 째 dfs
		idx += 1;
		maxDepth = 0;
		dfs1(max[0], -1, 0);// 트리 지름의 양끝 노드를 구하기 위한 두번 째 dfs
		mark(max[0], -1, 1);// 트리 지름을 탐색하며 visit에 마킹
		
		int max = 0;
		boolean validate = false;
		
		for(int i=1; i<=N; i++)
		{
			if(!visit[i] || adList[i].size() < 3)
				continue;
			
			for(int next : adList[i])
			{
				if(!visit[next])
				{
					validate = true;
					max = Math.max(max,dfs2(next));
				}
			}
		}
		
		System.out.print(validate ? size + max : 0);
	}
	static int dfs2(int now) {
		visit[now] = true;
		
		int maxSize = 0;
		
		for(int next : adList[now]) {
			if(visit[next])
				continue;
			
			visit[next] = true;
			
			int childSize = dfs2(next);
			
			maxSize = Math.max(maxSize, childSize);
		}
		return maxSize + 1;
	}
	static boolean mark(int now, int prev, int depth) {
		visit[now] = true;
		
		if(max[1] == now)
		{
			size = depth;
			return true;
		}
		
		for(int next : adList[now])
		{
			if(next != prev && !visit[next])
			{
				visit[next] = true;
				if(mark(next, now, depth + 1))
				{
					return true;
				}
				visit[next] = false;
			}
		}
		return false;
	}
	static void dfs1(int now, int prev, int depth) {
		if(maxDepth < depth)
		{
			maxDepth = depth;
			max[idx] = now;
		}
		for(int next : adList[now])
			if(next != prev)
				dfs1(next, now, depth + 1);
	}
}