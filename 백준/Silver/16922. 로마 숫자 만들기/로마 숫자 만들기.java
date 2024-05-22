// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	
	static final int 	arr[]	= {1,5,10,50};
	static boolean 		visit[]	= new boolean[1001];
	static int 			N, CNT;
	
	public static void DFS(int depth, int idx, int sum) {
		if(depth ==  N) 
		{ 
			if(!visit[sum]) 
			{
				visit[sum] = true;
				CNT++;
			}
			return;
		}
		for(int i=idx; i<4; i++)
			DFS(depth + 1, i, sum + arr[i]);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		DFS(0,0,0);
		
		System.out.print(CNT);
	}
}