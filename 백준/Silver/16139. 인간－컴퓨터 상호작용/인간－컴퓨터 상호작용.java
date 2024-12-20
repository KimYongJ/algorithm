//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/16139

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str		= br.readLine();
		int len			= str.length();
		int prevSum[][] = new int[26][len];	// 각 알파벳마다 누적합을 구한다.
		
		for(int i=0; i<26; i++)
		{
			if(str.charAt(0)-'a' == i)
				prevSum[i][0] = 1;
			for(int j=1; j<len; j++)
			{
				int c = str.charAt(j) - 'a';
				if(c == i)
					prevSum[i][j] = 1;
				prevSum[i][j] += prevSum[i][j-1];
			}
		}
		
		
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int i = st.nextToken().charAt(0) - 'a';
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			sb.append(0<s ? prevSum[i][e] - prevSum[i][s-1] : prevSum[i][e]).append('\n');
		}
		System.out.print(sb);
	}
}