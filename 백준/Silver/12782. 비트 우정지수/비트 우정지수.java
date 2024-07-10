// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		int _0, _1, len;
		String a,b;
		while(T-->0) 
		{
			st	= new StringTokenizer(br.readLine());
			_0	= 0;
			_1	= 0;
			a	= st.nextToken();
			b	= st.nextToken();
			len = a.length();
			for(int i=0; i<len; i++) 
			{
				char c = a.charAt(i);
				if(c != b.charAt(i)) 
				{
					if(c == '0') 
					{
						_0 ++;
					}
					else 
					{
						_1 ++;
					}
				}
			}
			sb.append(Math.max(_0, _1))
				.append('\n');
		}
		System.out.print(sb.toString());
	}
}