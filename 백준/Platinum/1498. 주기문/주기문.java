//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1498
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char text[] = br.readLine().toCharArray();
		int len		= text.length;
		int fail[]	= new int[len];
		
		for(int i=1,j=0; i<len; i++)
		{
			while(0<j && text[i] != text[j])
				j = fail[j - 1];
			if(text[i] == text[j])
				fail[i] = ++j;
		}
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<len; i++)
			if(fail[i] != 0)
			{
				int idx		= i + 1;
				int period	= idx - fail[i];
				if(idx % period == 0)
					sb.append(idx).append(' ').append(idx / period).append('\n');
			}

		System.out.print(sb.toString());
	}
}
