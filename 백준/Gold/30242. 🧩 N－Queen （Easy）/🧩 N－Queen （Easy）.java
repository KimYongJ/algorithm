//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/30242
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static int N;
	static int[] order;
	static boolean[] visit, pass;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N		= Integer.parseInt(br.readLine());
		order	= new int[N+1];
		visit	= new boolean[21];
		pass	= new boolean[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
		{
			order[i] = Integer.parseInt(st.nextToken());
			visit[order[i]] = true;
			if(order[i] != 0)
				pass[i] = true;
		}
		StringBuilder sb = new StringBuilder();
		if(back(1))
			for(int i=1; i<=N; i++)sb.append(order[i]).append(' ');
		else
			sb.append(-1);
		
		System.out.print(sb.toString());
	}
	public static boolean check(int row, int col) {
		for(int i=1; i<row; i++)
			if(Math.abs(row - i) == Math.abs(col - order[i]))
				return false;
		return true;
	}
	public static boolean back(int depth) {
		if(N < depth)
			return true;
		if(pass[depth]) {
			if(check(depth, order[depth]))
				return back(depth + 1);
			return false;
		}

		for(int i=1; i<=N; i++)
			if(!visit[i] && check(depth, i))
			{
				visit[i] = true;
				order[depth] = i;
				if(back(depth + 1))
					return true;
				visit[i] = false;
			}
		
		return false;
	}
}