// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, result[];
	static int adlist[][];
	static boolean visit[][];
	public static void print() {
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<N; i++)
			sb.append(result[i]).append('\n');
		System.out.print(sb);
	}
	public static void DFS(int depth) {
		if(depth == N) {
			print();
			System.exit(0);
		}
		for(int next : adlist[depth]) {
			if(next == 0) break;
			if(!visit[depth][next] && next != result[depth-1]) {
				visit[depth][next] = true;
				result[depth] = next;
				DFS(depth + 1);
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken())+1; 	// 날수 = 깊이
		result = new int[N];					// 날수(깊이) 에 따라 줄 떡
		adlist = new int[N][9];					// 해당 날에 가져갈 떡 종류
		visit  = new boolean[N][10];				// 방문 체크
		for(int i=1; i<N; i++) 
		{
			st = new StringTokenizer(br.readLine());
			int J = Integer.parseInt(st.nextToken());
			for(int j=0; j<J; j++) 
			{
				adlist[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		DFS(1);
		
		System.out.println(-1);
	}
}
