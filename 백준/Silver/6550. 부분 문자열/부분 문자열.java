// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static boolean get(String s, String t) {
		int len = t.length();
		int len2= s.length();
		int idx = 0;
		for(int i=0; i<len && idx < len2; i++) 
		{
			if(t.charAt(i) == s.charAt(idx)) 
			{
				idx++;
			}
		}
		return idx == len2;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		String str		   = br.readLine();
		
		while(str != null && str.length() > 0) 
		{
			String[] s = str.split(" ");
			sb.append(	get(s[0], s[1]) ? "Yes" : "No")
				.append('\n');
			str = br.readLine();
		}
		System.out.print(sb);
	}
}