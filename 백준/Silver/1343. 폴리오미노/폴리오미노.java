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
		int n;
		for(int i=0; i<len; i++) 
		{
			if(S.charAt(i) == '.') 
			{
				if(cnt % 2 == 0) 
				{
					n = cnt / 4;
					for(int a=0; a<n; a++) {sb.append(A);}
					n = (cnt%4) / 2;
					for(int b=0; b<n; b++) {sb.append(B);}
					sb.append('.');
				}
				else 
				{
					System.out.print(-1);
					return;
				}
				cnt = 0;
			}else {
				cnt++;
			}
		}
		if(cnt % 2 == 1) 
		{
			System.out.print(-1);
			return;
		}
		n = cnt / 4;
		for(int a=0; a<n; a++) {sb.append(A);}
		n = (cnt%4) / 2;
		for(int b=0; b<n; b++) {sb.append(B);}
		System.out.print(sb.toString());
	}
}