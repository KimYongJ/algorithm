//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/3779
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		int CASE = 0;
		while(true)
		{
			int N = Integer.parseInt(br.readLine());
			
			if(N == 0)
				break;
			
			String text = br.readLine();
			int len		= text.length();
			int fail[]	= new int[len];
			
			for(int i=1, j=0; i<len; i++)
			{
				while(0<j && text.charAt(i) != text.charAt(j))
					j = fail[j - 1];
				
				if(text.charAt(i) == text.charAt(j))
					fail[i] = ++j;
			}
			
			sb.append("Test case #").append(++CASE).append('\n');
			
			for(int i=1; i<len; i++)
				if(fail[i] != 0)
				{
					int idx		= i + 1;
					int period	= idx - fail[i];
					if(idx % period == 0)
						sb.append(idx).append(' ')
							.append(idx / period).append('\n');
				}

			sb.append('\n');
		}
		System.out.print(sb);
	}
}