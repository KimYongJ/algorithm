// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String args[])throws Exception{
		BufferedReader br	= new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb1	= new StringBuilder(br.readLine());
		StringBuilder sb2	= new StringBuilder();
		sb2.append(sb1.charAt(0));
		
		int len = sb1.length();
		for(int i=1; i<len; i++) 
		{
			char c = sb1.charAt(i);
			if(sb2.charAt(i-1) < c)
				sb2.insert(0, c);
			else
				sb2.append(c);
		}
		System.out.print(sb2.reverse().toString());
	}
}
