//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/16900
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String text = st.nextToken();					// 오십만
		long K		= Integer.parseInt(st.nextToken());	// 1<=백만
		int len		= text.length();
		int fail[]	= new int[len];
		
		for(int i=1,j=0; i<len; i++)
		{
			while(0<j && text.charAt(i) != text.charAt(j))
				j = fail[j - 1];
			
			if(text.charAt(i) == text.charAt(j))
				fail[i] = ++j;
		}
		System.out.print(len + ( (K-1) * (len - fail[len-1]) ) );
	}
}