//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/27962

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		String text = br.readLine();
		int len		= text.length();
		
		while(--len>=0)
		{
			int diff = 0;
			for(int i=0; i<len; i++)
				if(text.charAt(i) != text.charAt(N - len + i))
				{
					++diff;
					if(1 < diff)
						break;
				}
			
			if(diff == 1)
			{
				System.out.print("YES");
				return;
			}
		}
		
		System.out.print("NO");
	}
}