//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14607
//1초 / 512mb
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Integer.parseInt(br.readLine());	// 1<=십억
		
		System.out.print((N*N - N) >> 1);
	}
}