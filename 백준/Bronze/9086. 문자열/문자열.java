import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder 	sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		String str;
		while(T-->0) 
		{
			str = br.readLine();
			sb.append(str.charAt(0))
				.append(str.charAt(str.length()-1))
				.append('\n');
		}
		System.out.print(sb);
	}
}