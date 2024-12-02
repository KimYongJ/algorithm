//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2930
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int R			= Integer.parseInt(br.readLine());	// 라운드 수 R(1<=50)
		char [] me		= br.readLine().toCharArray();
		int N			= Integer.parseInt(br.readLine());
		char[][]friend	= new char[N][];
		int[][]dp		= new int[R][3];
		int score		= 0;
		for(int i=0; i<N; i++)
		{
			friend[i] = br.readLine().toCharArray();
			for(int r=0; r<R; r++)
			{
				score += cal(me[r], friend[i][r]);
				dp[r][0] += cal('S', friend[i][r]);	// r라운드 가위일 때 총점수 합
				dp[r][1] += cal('R', friend[i][r]);	// r라운드 바위일 때 총점수 합
				dp[r][2] += cal('P', friend[i][r]);	// r라운드 보일 때 총점수 합
			}
		}
		
		System.out.println(score);
		
		int max = 0;
		for(int r=0; r<R; r++)
			max += Math.max(Math.max(dp[r][0], dp[r][1]), dp[r][2]);
		
		System.out.println(max);
	}

	public static int cal(char me, char you)
	{
		if(me == you)				return 1;
		if(me == 'R' && you == 'S') return 2;
		if(me == 'S' && you == 'P') return 2;
		if(me == 'P' && you == 'R') return 2;
		return 0;
	}
}