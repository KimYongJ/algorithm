// https://github.com/kimyongj/algorithm
import java.util.ArrayList;

class Main{
	
	static int 		MAX;
	static int		len;
	static int 		sy, sx;
	static int 		N, M, H;
	static int		dist[][];
	static boolean 	visit[];
	static ArrayList<int[]> position = new ArrayList<>();

	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void backtracking(int beforeIdx, int cnt, int energy) {
		// 현재 위치에서 처음으로 갈 수 있으면서 cnt가 큰 경우 MAX값 갱신
		if(MAX < cnt && dist[0][beforeIdx] <= energy)	
			MAX = cnt;
		// 모두 다 밤운했으면 끝냄
		if(MAX == len-1) return;

		for(int i=1; i<len; i++)
			if(!visit[i]) 
			{
				visit[i] = true;
				if(dist[beforeIdx][i] <= energy)	// 갈수있는 거리면 간다.
					backtracking(i, cnt + 1, energy - dist[beforeIdx][i] + H);
				visit[i] = false;
			}
	}
	
	public static void main(String[] args)throws Exception{
		N		= read(); 							// 마을크기
		M 		= read(); 							// 초기체력
		H		= read(); 							// 회복양
		
		for(int y=0; y<N; y++) 
			for(int x=0; x<N; x++) 
			{
				int n = read();
				if(n == 1) 
				{
					sy = y;							// 시작위치 별도 기록
					sx = x; 						// 시작위치 별도 기록
				}else if(n == 2) 
					position.add(new int[] {y,x}); 	// 우유 위치 리스트에 저장
			}
		
		position.add(0,new int[] {sy,sx});			// 시작 위치를 0번째 인덱스에 삽입
		len		= position.size();
		visit 	= new boolean[len];					// 방문배열 초기화
		dist	= new int[len][len];				// 각 노드끼리의 거리를 미리 저장
		// 각 노드끼리의 거리를 미리 저장
		for(int i=0; i<len-1; i++)
			for(int j=i+1; j<len; j++) 
			{
				int start[] = position.get(i);
				int now[]	= position.get(j);
				int d		= Math.abs(start[0]-now[0]) + Math.abs(start[1]-now[1]);
				dist[i][j] = dist[j][i] = d;
			}
		
		backtracking(0,0,M);
		
		System.out.print(MAX);
	}
}