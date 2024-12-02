//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2930
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int R			= Integer.parseInt(br.readLine());	// 라운드 수 R(1<=50)
		char[] me		= br.readLine().toCharArray();
		int N			= Integer.parseInt(br.readLine());
		int score		= 0;
		int max			= 0;
		char[][] you	= new char[N][]; 
		
		for(int i=0; i<N; i++)
			you[i] = br.readLine().toCharArray();
		
		for(int r=0; r<R; r++)
		{
			int a = 0;
			int b = 0;
			int c = 0;
			for(int i=0; i<N; i++)
			{
				score += cal(me[r], you[i][r]);
				a += cal('S', you[i][r]);	// r라운드 가위일 때 총점수 합
				b += cal('R', you[i][r]);	// r라운드 바위일 때 총점수 합
				c += cal('P', you[i][r]);	// r라운드 보일 때 총점수 합
			}
			max += Math.max(a, Math.max(b, c));
		}

		System.out.println(score);
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