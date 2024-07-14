// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br	= new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb	= new StringBuilder();
		int T 				= Integer.parseInt(br.readLine());
		while(T-->0) 
		{
			int len = Integer.parseInt(br.readLine());
			String s1 = br.readLine(); // 말의 초기상태
			String s2 = br.readLine(); // 목표 상태
			int w = 0, b = 0; // w와 b의 개수
			
			for(int i=0; i<len; i++)
			{
				if(s1.charAt(i) != s2.charAt(i)) 
				{
					if(s1.charAt(i) == 'W') 
					{
						w++;
					}else 
					{
						b++;
					}
				}
			}
			int min = Math.min(w, b);
			int max = Math.max(w, b) - min;
			sb.append(min + max).append('\n');			
		}
		System.out.print(sb.toString());
	}
}