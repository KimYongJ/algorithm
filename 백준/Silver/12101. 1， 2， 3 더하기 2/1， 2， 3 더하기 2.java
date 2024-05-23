// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, K, num[];
	static int cnt;
	
	public static void exit() {
		StringBuilder sb = new StringBuilder();
		for(int n : num) {
			if(n == 0)break;
			sb.append(n).append('+');
		}
		System.out.print(sb.deleteCharAt(sb.length()-1));
		System.exit(0);
	}
	public static void DFS(int depth, int sum) {
		if(sum == N){
			if(++cnt == K)
				exit();
		}
		if(sum > N || depth == N) return;	
		
		for(int i=1; i<=3; i++) 
		{
			num[depth] = i;
			DFS(depth + 1, sum + i);
			num[depth] = 0;
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N 	= Integer.parseInt(st.nextToken());
		K 	= Integer.parseInt(st.nextToken());
		num = new int[N];
		
		DFS(0,0);
		
		System.out.print(-1);
	}
}