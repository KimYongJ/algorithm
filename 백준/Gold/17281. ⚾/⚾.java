//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17281
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static boolean visit[];
	static int max, N, order[], round[][];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N		= Integer.parseInt(br.readLine()); //  N(2 ≤ N ≤ 50)
		round	= new int[N+1][10];
		order	= new int[10];
		visit	= new boolean[10];
		order[4]= 1;					// 1번 선수는 4번 타자이다.
		
		for(int y=1; y<=N; y++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			//각 선수가 각 이닝에서 얻는 결과가 1번 이닝부터 N번 이닝까지 순서대로 주어진다. 이닝에서 얻는 결과는 9개의 정수가 공백으로 구분되어져 있다. 각 결과가 의미하는 정수는 다음과 같다.
			for(int x=1; x<=9; x++)
				round[y][x] = Integer.parseInt(st.nextToken());
		}
		
		bruteforce(1);
		
		System.out.print(max);
	}
	public static void cal() {
		int sum		= 0;
		int r		= 0;
		int i		= 1;
		while(++r <= N)
		{
			int out = 0;
			int ground[]= new int[4];
			while(out != 3)
			{
				switch(round[r][order[i]])
				{
				case 0:// 아웃
					++out;
					break;
				case 1:// 안타
					sum += ground[3];
					ground[3] = ground[2];
					ground[2] = ground[1];
					ground[1] = 1;
					break;
				case 2:// 2루타
					sum += ground[3] + ground[2];
					ground[3] = ground[1];
					ground[2] = 1;
					ground[1] = 0;
					break;
				case 3:// 3루타
					sum += ground[3] + ground[2] + ground[1];
					ground[2] = ground[1] = 0;
					ground[3] = 1;
					break;
				case 4:// 홈런
					sum += ground[3] + ground[2] + ground[1] + 1;
					ground[3] = ground[2] = ground[1] = 0;
					break;
				}
				if(++i == 10)
					i = 1;
			}
		}
		max = Math.max(max, sum);
	}
	public static void bruteforce(int depth) {
		if(depth == 10)
		{
			cal();
			return;
		}
		if(depth == 4)			// 4번타자는 1번으로 정해져있음
		{
			bruteforce(depth + 1);
			return;
		}
		for(int i=2; i<=9; i++)
			if(!visit[i])
			{
				visit[i] = true;
				order[depth] = i;
				bruteforce(depth + 1);
				visit[i] = false;
			}
	}
}