// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		StringBuilder	str;
		StringTokenizer st;
		char	c, first;
		int		N, T = Integer.parseInt(br.readLine());
		while(T-->0) 
		{
			first	= 'a';
			str		= new StringBuilder();
			N		= Integer.parseInt(br.readLine());
			st		= new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) 
			{
				c = st.nextToken().charAt(0);
				if(first >= c) 
				{
					first = c;
					str.insert(0,c);
				}
				else 
				{
					str.append(c);
				}
			}
			sb.append(str.toString()).append('\n');
		}
		System.out.print(sb.toString());
	}
}