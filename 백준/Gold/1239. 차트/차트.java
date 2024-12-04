//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1239
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int CNT;
	static int N;
	static int [] origin, order;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N		= Integer.parseInt(br.readLine());
		origin	= new int[N];
		order	= new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int max = 0;
		for(int i=0; i<N; i++)
			max = Math.max(max,origin[i] = Integer.parseInt(st.nextToken()));
		
		if(max == 50)
			System.out.print(1);
		else if(50 < max)
			System.out.print(0);
		else
		{
			bruteforce(0,0);
			System.out.print(CNT / 2);
		}
	}
	public static void calc() {
		int cnt = 0;
		for(int i=0; i<N; i++)
		{
			int sum = 0;
			int idx = i;
			while(true) {
				sum += order[idx];
				if(sum == 50) {
					++cnt;
					break;
				}
				if(50 < sum)
					break;
				
				idx = (idx + 1) % N;
			}
		}
		CNT = Math.max(CNT, cnt);
	}
	public static void bruteforce(int depth, int bitmask) {
		if(depth == N)
		{
			calc();
			return;
		}
		for(int i=0; i<N; i++)
			if((bitmask & (1<<i)) == 0)
			{
				order[depth] = origin[i];
				bruteforce(depth + 1, bitmask | (1<<i));
			}
	}
}