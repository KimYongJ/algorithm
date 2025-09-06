//https://www.acmicpc.net/problem/12756
//1초 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		double a1 = Integer.parseInt(st.nextToken());
		double b1 = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		double a2 = Integer.parseInt(st.nextToken());
		double b2 = Integer.parseInt(st.nextToken());
		
		int res1 = (int)Math.ceil(b1 / a2);
		int res2 = (int)Math.ceil(b2 / a1);
		String str = "DRAW";
		if(res1 > res2)
			str = "PLAYER A";
		else if(res1 < res2)
			str = "PLAYER B";
		
		System.out.print(str);
	}
}