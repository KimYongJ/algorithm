//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1443
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int D, P;
	static int limit;
	static int max = -1;
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		D		= Integer.parseInt(st.nextToken());	// 표시할 수 있는 최대 자리수(2<=8)
		P		= Integer.parseInt(st.nextToken());	// 곱할 수 있는 수(0<=30)
		limit	= (int)Math.pow(10, D);
		
		bruteforce(0, 1, 2);
		
		System.out.print(max);
	}
	public static void bruteforce(int depth, int num, int idx) {
		if(depth == P)
		{
			max = Math.max(max, num);
			return;
		}
		
		for(int i=Math.max(2,idx); i<=9; i++)
		{
			int next = num*i;
			if(next < limit)
				bruteforce(depth + 1, next, i);
		}
	}
}
