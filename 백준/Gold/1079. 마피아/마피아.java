//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1079
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static int			res, M, N;
	static int[]		origin;
	static int[][]		R;
	static boolean[]	kill;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N		= Integer.parseInt(br.readLine());
		origin	= new int[N];
		R		= new int[N][N];
		kill	= new boolean[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			origin[i] = Integer.parseInt(st.nextToken());

		for(int y=0; y<N; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<N; x++)
				R[y][x] = Integer.parseInt(st.nextToken());
		}

		M = Integer.parseInt(br.readLine());

		bruteforce(0, N, origin);
		
		System.out.print(res);
	}
	
	public static void bruteforce(int day, int people, int[] guilt) {
		if(people < 2)
		{
			res = Math.max(res, day);
			return;
		}

		if(people % 2 == 0)	// 참가자 짝수 = 밤(마피아가 타인 킬)
		{
			for(int i=0; i<N; i++)
			{
				if(!kill[i] && i != M)
				{
					kill[i] = true;
					for(int j=0; j<N; j++)
						guilt[j] += R[i][j];
					bruteforce(day + 1, people - 1, guilt);
					for(int j=0; j<N; j++)
						guilt[j] -= R[i][j];
					kill[i] = false;
				}
			}
			
		}
		else				// 홀수 = 낮(시민이 한명 킬)
		{
			int max = 0;
			int idx = 0;
			
			for(int i=0; i<N; i++)
				if(!kill[i] && max < guilt[i])
				{
					max = guilt[i];
					idx = i;
				}
			
			if(idx == M)
			{
				res = Math.max(res, day);
			}
			else
			{
				kill[idx] = true;
				bruteforce(day , people - 1, guilt);
				kill[idx] = false;
			}
		}
	}
}