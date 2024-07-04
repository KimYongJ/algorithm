// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0) 
		{
			int num = Integer.parseInt(br.readLine()) * 100;
			sb.append(num / 2500).append(' ');
			num %= 2500;
			sb.append(num / 1000).append(' ');
			num %= 1000;
			sb.append(num / 500).append(' ');
			sb.append((num %= 500)/100).append('\n');
		}
		System.out.print(sb.toString());
	}
}