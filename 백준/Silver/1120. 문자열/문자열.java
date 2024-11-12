//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1120

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String s1	= st.nextToken();
		String s2	= st.nextToken();
		int LEN		= s2.length() - s1.length();
		int MIN		= Integer.MAX_VALUE;
		
		for(int i=0; i<=LEN && MIN != 0; i++)
		{
			int idx = i;
			int cnt	= 0;
			for(char c : s1.toCharArray())
				if(c != s2.charAt(idx++))
					++cnt;
			MIN = Math.min(cnt, MIN);
		}
		System.out.print(MIN);
	}
}