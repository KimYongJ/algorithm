//https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String str = br.readLine();
		while(str != null) {
			sb.append(str).append('\n');
			str = br.readLine();
		}
		System.out.print(sb.toString());
	}
}