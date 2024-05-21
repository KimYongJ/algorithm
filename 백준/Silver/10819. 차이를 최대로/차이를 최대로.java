// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int 		N, max, base[];
	static boolean 	visit[];

	public static void BACK(int depth,int beforeIdx, int sum) {
		if(depth == N) {
			if(max < sum)
				max = sum;
			return;
		}
		for(int i=0; i<N; i++)
			if(!visit[i]) 
			{
				visit[i] = true;
				BACK(depth+1, i, sum + Math.abs(base[beforeIdx] - base[i]));
				visit[i] = false;
			}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N 		= Integer.parseInt(br.readLine());
		max 	= Integer.MIN_VALUE;
		base 	= new int[N];
		visit	= new boolean[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++)
			base[i] = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) 
		{
			visit[i] = true;
			BACK(1, i, 0);
			visit[i] = false;
		}
		
		
		System.out.print(max);
	}	
}