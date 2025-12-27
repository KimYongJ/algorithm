//https://www.acmicpc.net/problem/14298
//5초 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.StringTokenizer;

class Main{
	
	static int arr[];
	static int len, total;
	static double win;
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int i=1; i<=T; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			total = 0;
			win = 0;
			len = a + b;
			arr = new int[len];
			
			dfs(0, a,b);
			
			sb.append("Case #").append(i).append(": ")
			.append(String.format(Locale.US, "%.6f", win / total)).append('\n');
		}
		System.out.print(sb);
	}
	static void dfs(int depth, int a, int b) {
		if(len == depth)
		{
			++total;
			
			int Acnt = 0;
			int Bcnt = 0;
			for(int r : arr)
			{
				if(r == 1) Acnt++;
				if(r == 2) Bcnt++;
				if(Acnt <= Bcnt)
					return;
			}
			win++;
			return;
		}
		if(a > 0) {
			arr[depth] = 1;
			dfs(depth + 1, a - 1, b);
		}
		if(b > 0) {
			arr[depth] = 2;
			dfs(depth + 1, a, b - 1);
		}
	}
}