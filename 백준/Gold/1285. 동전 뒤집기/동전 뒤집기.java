// https://github.com/kimyongj/algorithm
// https://www.acmicpc.net/problem/1285
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N			= Integer.parseInt(br.readLine());// 범위 0<=20
		boolean arr[][]	= new boolean[N][N];
		for(int i=0; i<N; i++)
		{
			String str = br.readLine();
			for(int j=0; j<N; j++)
				arr[i][j] = str.charAt(j) == 'T';
		}
		
		int ans = Integer.MAX_VALUE;
		
		for(int s=0; s<(1<<N); s++)
		{
			int sum = 0;
			for(int x=0; x<N; x++) 
			{
				int Tcnt = 0;
				for(int y=0; y<N; y++) 
				{
					boolean flag = arr[y][x];
					
					if((s&(1<<y)) != 0) 
						flag = !flag;
					
					if(flag)
						Tcnt++;
				}
				sum += Math.min(Tcnt,  N-Tcnt);
			}
			ans = Math.min(ans, sum);
		}
		System.out.print(ans);
	}
}