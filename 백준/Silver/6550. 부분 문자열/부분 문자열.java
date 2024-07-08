// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static boolean get(String s, String t) {
		int len1= t.length();
		int len2= s.length();
		int idx = 0;
		for(int i=0; i<len1; i++) 
		{
			if(t.charAt(i) == s.charAt(idx)) 
			{
				if(++idx == len2) 
				{
					return true;
				}
			}
		}
		return false;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		String str		   = br.readLine();
		while(str != null && !str.isEmpty()) 
		{
			String[] s = str.split(" ");
			
			sb.append(	get(s[0], s[1]) ? "Yes" : "No")
				.append('\n');
			
			str = br.readLine();
		}
		System.out.print(sb);
	}
}