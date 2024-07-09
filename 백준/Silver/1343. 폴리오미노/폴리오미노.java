// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		String S = br.readLine();
		String A = "AAAA";
		String B = "BB";
		int cnt = 0;
		int len = S.length();
		for(int i=0; i<len; i++) 
		{
			if(S.charAt(i) == '.') 
			{
				if(cnt % 2 == 0) 
				{
					sb.append(A.repeat(cnt/4));
					sb.append(B.repeat((cnt%4) / 2));
					sb.append('.');
				}
				else 
				{
					break;
				}
				cnt = 0;
			}else {
				cnt++;
			}
		}
		if(cnt % 2 == 1) 
		{
			System.out.print(-1);
		}
		else 
		{
			sb.append(A.repeat(cnt/4));
			sb.append(B.repeat((cnt%4) / 2));
			System.out.print(sb.toString());
		}
	}
}