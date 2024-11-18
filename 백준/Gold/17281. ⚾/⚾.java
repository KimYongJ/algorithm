//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17281
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{

	static int max, N, order[], round[][];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N		= Integer.parseInt(br.readLine()); //  N(2 ≤ N ≤ 50)
		round	= new int[N+1][10];
		order	= new int[10];
		order[4]= 1;					// 1번 선수는 4번 타자이다.
		
		for(int y=1; y<=N; y++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int x=1; x<=9; x++)
				round[y][x] = Integer.parseInt(st.nextToken());
		}
		
		bruteforce(1, 1<<1);
		
		System.out.print(max);
	}
	public static void bruteforce(int depth, int visit) {
		if(depth == 10)
		{
			cal();
			return;
		}
		if(depth == 4)			// 4번타자는 1번으로 정해져있음
		{
			bruteforce(depth + 1, visit);
			return;
		}
		for(int i=2; i<=9; i++)
		{
			int bit = 1<<i;
			if((visit & bit) == 0)
			{
				visit |= bit;
				order[depth] = i;
				bruteforce(depth + 1, visit);
				visit ^= bit;
			}
		}
	}
	public static void cal() {
		int sum		= 0;
		int r		= 0;
		int i		= 1;
		while(++r <= N)
		{
			int out		= 0;
			int ground	= 0;
			while(out != 3)
			{
				int shift = round[r][order[i]];
				if(shift == 0)
					++out;
				else {
					int next = (ground<<shift) + (1<<--shift);
					ground = next % (1<<3);
					sum += Integer.bitCount(next) - Integer.bitCount(ground);
				}
				if(++i == 10)
					i = 1;
			}
		}
		max = Math.max(max, sum);
	}
}