//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/30242
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static int N;
	static int[] order;
	static boolean[] visit, pass, dig1, dig2;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N		= Integer.parseInt(br.readLine());
		order	= new int[N+1];
		visit	= new boolean[21];
		pass	= new boolean[N+1];
		dig1	= new boolean[2*N+2];
		dig2	= new boolean[2*N+2];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
		{
			order[i] = Integer.parseInt(st.nextToken());
			visit[order[i]] = true;
			if(order[i] != 0)
			{
				pass[i] = true;
				dig1[i - order[i] + N] = dig2[i + order[i]] = true;
			}
		}
		StringBuilder sb = new StringBuilder();
		if(back(1))
			for(int i=1; i<=N; i++)sb.append(order[i]).append(' ');
		else
			sb.append(-1);
		
		System.out.print(sb.toString());
	}
	public static boolean back(int depth) {
		if(N < depth)
			return true;
		if(pass[depth])
			return back(depth + 1);

		for(int i=1; i<=N; i++)
			if(!visit[i] && !dig1[depth - i + N] && !dig2[depth + i])	// 열과 대각선체크를 동시에
			{
				visit[i] = true;
				dig1[depth - i + N] = dig2[depth + i] = true;
				order[depth] = i;
				if(back(depth + 1))
					return true;
				dig1[depth - i + N] = dig2[depth + i] = false;
				visit[i] = false;
			}
		
		return false;
	}
}
