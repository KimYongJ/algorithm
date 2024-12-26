//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/9646
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static int cnt;
	static int N, Y;
	static int len[];
	static int map[][];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			String str = br.readLine();
			if(str == null || str.length() == 0)
				break;
			st	= new StringTokenizer(str);
			cnt = 0;
			Y	= Integer.parseInt(st.nextToken());	// 행개수, 1<=7
			map = new int[10][10];
			len = new int[10];
			
			for(int i=1; i<=Y; i++)
				len[i] = Integer.parseInt(st.nextToken());
			
			N	= Integer.parseInt(br.readLine());	// 입력 가능한 최대수 (K<=7)
			
			bruteforce(1, 1);
			
			sb.append(cnt).append('\n');
		}
		System.out.print(sb);
	}
	public static void bruteforce(int r, int c) {
		if(r == 1 && len[1] < c)
		{
			++cnt;
			return;
		}
		int left = map[r][c - 1];
		int up = map[r - 1][c];
		for(int i=Math.max(up + 1, left); i<=N; i++)
		{
			map[r][c] = i;
			if(c <= len[r + 1])
				bruteforce(r + 1, c);
			else
				bruteforce(1, c + 1);
		}
	}
}
