// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int 		N, S[], B[];
	static int		result;
	static boolean 	visit[];
	
	public static void Back(int depth,int mul, int sum, int idx) {
		if(depth > 0)				// 모든 재료의 경우에 대해서 결과 탐색
			result = Math.min(result, Math.abs(mul - sum));
		
		if(depth == N) 				// 끝일 때 종료 
			return;

		for(int i=idx; i<N; i++) 
			if(!visit[i]) 			// 방문하지 않았을 때만 연산실행
			{
				visit[i] = true;	// 백트레킹
				Back(depth + 1, mul * S[i] , sum + B[i], i+1); // 조합이기 때문에 i + 1로 탐색
				visit[i] = false;
			}
		
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		S = new int[N];		// 곱
		B = new int[N];		// 합
		visit = new boolean[N];
		result = Integer.MAX_VALUE;
		for(int i=0; i<N; i++) 
		{
			st	= new StringTokenizer(br.readLine());
			S[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
		}

		Back(0, 1, 0, 0);

		System.out.print(result);
	}
}