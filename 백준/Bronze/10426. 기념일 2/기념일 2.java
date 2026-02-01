//https://www.acmicpc.net/problem/10426
//1초 256MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String str = st.nextToken();
		int plus = Integer.parseInt(st.nextToken()) - 1;
		
		System.out.print(LocalDate.parse(str).plusDays(plus));
	}
}