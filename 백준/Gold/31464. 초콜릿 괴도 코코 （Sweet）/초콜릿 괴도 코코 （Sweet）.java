//https://www.acmicpc.net/problem/31464
//2초 1024MB
//3 // 초콜릿의 크기(2<=40)
//### // 초콜릿 상태가 주어짐, '#'이면 초콜릿, '.'이면 초콜릿이 없음을 의미
//#.#
//###
//출력은 2단계 조건 충족하는 서로 다른 단위 초콜릿 개수를 출력하고 이런 초콜릿의 위치를 행, 열 순서로 기준 오름차순 출력한다.
//8
//1 1
//1 2
//1 3
//2 1
//2 3
//3 1
//3 2
//3 3
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main{
	
	static final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int N, vertex, edgeCnt;
	static boolean[][] map, visit;
	static List<int[]> list, ans;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new boolean[N + 2][N + 2];
		list = new ArrayList<>();
		ans = new ArrayList<>();
		
		for(int y=1; y<=N; y++)
		{
			String str = br.readLine();
			
			for(int x=1; x<=N; x++)
			{
				map[y][x] = str.charAt(x-1) == '#';// true면 초콜릿으로 갈 수 있음을 의미
				if(map[y][x])
					list.add(new int[] {y,x});
			}
		}
		
		int cnt = list.size();
		
		for(int i=0; i<list.size(); i++)
		{
			int [] a = list.get(i);
			
			map[a[0]][a[1]] = false;// 해당 좌표에서 초콜릿 제거
			
			if(isConnAndTree(cnt - 1, i))// 하나의 연결요소 이면서 트리인지 판별
				ans.add(a);
			
			map[a[0]][a[1]] = true;// 초콜릿 복귀
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(ans.size()).append('\n');
		
		for(int[] l : ans)
			sb.append(l[0]).append(' ').append(l[1]).append('\n');
		
		System.out.print(sb);
	}
	static boolean isConnAndTree(int remain, int idx) {
		if(remain <= 0)// 방문해야할 노드가 0이하면 false
			return false;
		
		idx = idx == 0 ? 1 : 0; // 시작 좌표를 알기 위한 idx

		int sy = list.get(idx)[0];
		int sx = list.get(idx)[1];
		
		vertex = 0;// 방문 정점
		edgeCnt = 0;// 방문 간선
		visit = new boolean[N + 2][N + 2];
		
		dfs(sy, sx);
		
		if(vertex != remain) // 방문한 정점과 방문해야할 정점이 같지 않다면 false 리턴
			return false;

		// 트리인지 판별하는 공식
		return vertex - 1 == edgeCnt / 2;
	}
	static void dfs(int y, int x) {
		if(visit[y][x])
			return;
		
		visit[y][x] = true;
		vertex++;
		
		for(int xy[] : dxy)
		{
			int ny = y + xy[0];
			int nx = x + xy[1];
			
			if(!map[ny][nx])// 초콜릿이 아니면 스킵
				continue;
			
			edgeCnt++;// 모든 무향 간선이 양쪽에서 한 번씩 세어져 총 2회 카운트됨 → edgeCnt/2가 실제 무향 간선 수(E)
			
			dfs(ny, nx);
		}
	}
}