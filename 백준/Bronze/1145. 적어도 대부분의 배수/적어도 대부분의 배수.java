//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1145
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static int min	= 1<<30;
	static int num[]= new int[5];
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		for(int i=0; i<5; i++)
			num[i] = Integer.parseInt(st.nextToken());
		
		bruteforce(0, 0, 1);
		
		System.out.print(min);
	}
	public static void bruteforce(int idx, int depth, int lcm) {
		if(depth == 3)
		{
			min = Math.min(min, lcm);
			return;
		}
		if(min < lcm)
			return;
		for(int i=idx; i<5; i++)
		{
			int nextLcm = LCM(lcm, num[i]);
			bruteforce(i + 1, depth + 1, nextLcm);
		}
	}
	public static int LCM(int a, int b)
	{return a*b / GCD(a,b);}
	public static int GCD(int a, int b)
	{return b == 0 ? a : GCD(b, a % b);}
}