// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, max, base[], arr[];
	static boolean visit[];
	public static void cal() {
		int sum = 0;
		for(int i=1; i<N; i++)
			sum += Math.abs(arr[i-1] - arr[i]);
		if(max < sum)
			max = sum;
	}
	public static void BACK(int depth) {
		if(depth == N) {
			cal();
			return;
		}
		for(int i=0; i<N; i++)
			if(!visit[i]) 
			{
				visit[i] = true;
				arr[depth] = base[i];
				BACK(depth+1);
				visit[i] = false;
			}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		max = Integer.MIN_VALUE;
		base = new int[N];
		arr = new int[N];
		visit = new boolean[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			base[i] = Integer.parseInt(st.nextToken());
		
		BACK(0);
		
		System.out.print(max);
	}	
}