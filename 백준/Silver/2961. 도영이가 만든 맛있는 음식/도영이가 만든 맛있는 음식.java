// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int 		N, S[], B[];
	static int		DEPTH, result;
	static boolean 	visit[];
	
	public static void Back(int depth,int mul, int sum) {
		if(depth == DEPTH) {
			result = Math.min(result, Math.abs(mul - sum));
			return;
		}
		for(int i=0; i<N; i++) 
			if(!visit[i]) {
				visit[i] = true;
				Back(depth + 1, mul * S[i] , sum + B[i]);
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
		
		for(DEPTH=1; DEPTH<=N; DEPTH++) // 총 몇개의 재로로 할건지 체크, 1개 재료부터 N재료까지
		{
			Back(0,1,0);
		}
		System.out.print(result);
	}
}