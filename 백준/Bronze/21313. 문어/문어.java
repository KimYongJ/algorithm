//https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		boolean flag = false;
		if(N % 2 != 0) {
			N--;
			flag = true;
		}
		String str = "1 2 ";
		for(int i=0; i<N/2; i++) {
			sb.append(str);
		}
		if(flag) {
			sb.append(3);
		}
		System.out.print(sb.toString());
	}
}