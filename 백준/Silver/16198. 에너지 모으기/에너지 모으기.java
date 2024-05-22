// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, NN, MAX, nums[];
	static boolean visit[];
	public static int getMul(int idx) {
		int left=0, right=0;
		for(int l=idx-1,r=idx+1; left==0 || right==0; l--,r++) 
		{
			if(left==0  && !visit[l]) 	left  = nums[l];
			if(right==0 && !visit[r]) 	right = nums[r];
		}
		return left*right;
	}

	public static void DFS(int depth, int sum) {
		if(depth == NN) {
			if(MAX < sum) MAX = sum;
			return;
		}
		for(int i=1; i<=NN; i++)
			if(!visit[i]) 
			{
				visit[i] = true;
				DFS(depth + 1, sum + getMul(i));
				visit[i] = false;
			}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N 		= Integer.parseInt(br.readLine());
		NN		= N-2;
		nums	= new int[N];
		visit	= new boolean[N];
		
		st 		= new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) 
			nums[i] = Integer.parseInt(st.nextToken());
		
		DFS(0,0);
		
		System.out.print(MAX);
	}
}