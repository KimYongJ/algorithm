//https://www.acmicpc.net/problem/25592
//1초 1024MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int P = 1;
		
		while(N > 0)
			N -= P++;
		
		int ans = 0;
		
		boolean bool = (P&1) == 1;
 
		if(bool && N == 0) ans = P;
		if(!bool && N < 0) ans = -N;
		
		System.out.print(ans);
	}
}