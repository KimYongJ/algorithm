//https://www.acmicpc.net/problem/32343
//1초 1024

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int num = (1<<N) - 1;
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		int D = Math.abs(X + Y - N);
		
		if(D != 0)
			num -= ((1<<D)-1);

		System.out.print(num);
	}
}