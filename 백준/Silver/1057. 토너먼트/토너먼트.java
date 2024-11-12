//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1057
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Integer.parseInt(st.nextToken());
		int A	= Integer.parseInt(st.nextToken());
		int B	= Integer.parseInt(st.nextToken());
		int res	= 0;

		while(A != B)
		{
			A = A/2 + A%2;
			B = B/2 + B%2;
			++res;
		}
		System.out.print(res);
	}
}