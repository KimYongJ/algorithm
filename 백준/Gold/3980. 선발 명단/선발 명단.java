import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	static int T, max, map[][];
	static boolean visit[];
	public static void backtracking(int depth, int sum) 
	{
		if(depth == 11) 
		{
			if(max < sum) 
				max = sum;
			return;
		}
		for(int j=0; j<11; j++)
		{
			if(!visit[j] && map[depth][j] != 0) 
			{
				visit[j] = true;
				backtracking(depth+1, sum+map[depth][j]);
				visit[j] = false;
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br 	= new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb 	= new StringBuilder();
		T 					= Integer.parseInt(br.readLine());
		StringTokenizer st;
		while(T-->0) 
		{
			max 	= 0;
			map 	= new int[11][11];
			visit	= new boolean[11];
			for(int i=0; i<11; i++) 
			{
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<11; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			backtracking(0,0);
			
			sb.append(max).append('\n');
		}
		System.out.println(sb);
	}
}