// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int 		N, MAX, nums[];
	static boolean 	visit[];
	
	public static int getMul(int l, int r) {
		while(visit[l] || visit[r]) 
		{
			if(visit[l])l--;
			if(visit[r])r++;
		}
		return nums[l] * nums[r];
	}

	public static void DFS(int depth, int sum) {
		if(depth == N) 
		{
			if(MAX < sum) 
				MAX = sum;
			return;
		}
		for(int i=1; i<=N; i++)
			if(!visit[i]) 
			{
				visit[i] = true;
				DFS(depth + 1, sum + getMul(i-1, i+1));
				visit[i] = false;
			}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N 		= Integer.parseInt(br.readLine());
		nums	= new int[N];
		visit	= new boolean[N];
		
		st 		= new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) 
			nums[i] = Integer.parseInt(st.nextToken());
		
		N		-= 2; // 빠른 연산을 위한 전처리 
		
		DFS(0,0);
		
		System.out.print(MAX);
	}
}