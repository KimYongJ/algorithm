//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2702

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a	= Integer.parseInt(st.nextToken());
			int b	= Integer.parseInt(st.nextToken());
			int GCD = GCD(a, b);
			int LCM	= a*b/GCD;
			
			sb.append(LCM).append(' ').append(GCD).append('\n');
		}
		System.out.print(sb.toString());
	}
	public static int GCD(int a, int b) {
		return b == 0 ? a : GCD(b, a%b);
	}
}