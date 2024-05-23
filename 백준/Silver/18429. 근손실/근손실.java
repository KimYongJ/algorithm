// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int 		N, K, num, kit[];
	static int 		result;
	static boolean 	visit[];
	
	public static void Backtracking(int depth, int sum) {
		if(depth == N) {
			if(sum >= 500) result++;
			return;
		}
		for(int i=0; i<N; i++)
			if(!visit[i]) {
				visit[i] = true;
				num = sum + kit[i] - K;
				if(num >= 500) {
					Backtracking(depth + 1, num);
				}
				visit[i] = false;
			}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		kit = new int[N];
		visit = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			kit[i] = Integer.parseInt(st.nextToken());
		
		Backtracking(0,500);
		
		System.out.print(result);
	}
}