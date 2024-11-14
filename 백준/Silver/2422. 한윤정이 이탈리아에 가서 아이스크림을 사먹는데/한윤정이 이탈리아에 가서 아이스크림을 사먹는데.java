//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2422
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static boolean pair[][];
	static int ice[];
	static int N, M, cnt;
	
	public static void bruteforce(int depth, int idx) {

		if(depth == 4)
		{
			if(!pair[ice[1]][ice[3]])
				++cnt;
			return;
		}
		for(int i=idx; i<=N; i++)
		{
			ice[depth] = i;
			if(!pair[ice[depth]][ice[depth-1]])
				bruteforce(depth + 1, i + 1);
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());
		M		= Integer.parseInt(st.nextToken());
		ice		= new int[4];
		pair	= new boolean[N+1][N+1];
		
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			pair[a][b] = pair[b][a] = true;
		}
		
		bruteforce(1, 1);
		
		System.out.print(cnt);
	}
}