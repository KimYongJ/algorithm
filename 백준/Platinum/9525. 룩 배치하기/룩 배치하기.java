import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main{
	
	static int N;
	static int yIdx, xIdx;
	static int ymap[][];// y좌표의 인덱스들을 담을 배열
	static int xmap[][];// x좌표의 인덱스들을 담을 배열
	static boolean isPawn[][];// 가로막는 폰인지 아닌지를 담을 배열
	static List<Integer> adList[];// 인접리스트
	// 이분 매칭시 사용
	static int time;// 이분 매칭시 방문 표시를 체킹할 변수
	static int match[];// 매칭된 값을 저장할 변수
	static int visitTime[];// 노드가 방문했는지를 체크할 배열
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		ymap = new int[N + 1][N + 1];
		xmap = new int[N + 1][N + 1];
		isPawn = new boolean[N + 1][N + 1];
		adList = new ArrayList[N * N + 1];
		
		for(int i=0; i<adList.length; i++)
			adList[i] = new ArrayList<>();
		
		for(int y=1; y<=N; y++)
		{
			String str = br.readLine();
			for(int x=1; x<=N; x++)
			{
				isPawn[y][x] = str.charAt(x-1) == 'X';
				if(!isPawn[y][x])// 빈칸인 경우 
				{
					// y인덱스는 위쪽만 바라보고 위쪽 값이 정해져 있다면 그 값으로, 안정해져있다면 새로운 행을 생성합니다.
					ymap[y][x] = ymap[y-1][x] == 0 ? ++yIdx : ymap[y-1][x];
					// x인덱스는 왼쪽만 바라보고 왼쪽 값이 정해져 있다면 그 값으로, 안정해져있다면 새로운 열을 생성합니다.
					xmap[y][x] = xmap[y][x-1] == 0 ? ++xIdx : xmap[y][x-1];
					// 모든 빈칸에 대해서 인접 리스트를 생성합니다.
					adList[ymap[y][x]].add(xmap[y][x]);
				}
			}
		}
		
		match = new int[xIdx + 1];
		visitTime = new int[xIdx + 1];
		
		int cnt = 0;
		for(int y=1; y<=yIdx; y++)
		{
			++time;
			if(dfs(y))
				++cnt;
		}
		System.out.print(cnt);
	}
	static boolean dfs(int y) {
		for(int x : adList[y])
		{
			// 이미 방문했다면 스킵
			if(visitTime[x] == time)
				continue;
			// 방문 체크
			visitTime[x] = time;
			// 현재 x좌표가 방문하지 않았거나, 여기 방문한 노드가 다른곳으로 옮길 수 있다면, x에 y를 넣고 return true;
			if(match[x] == 0 || dfs(match[x]))
			{
				match[x] = y;
				return true;
			}
		}
		return false;
	}
}
