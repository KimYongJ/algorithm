// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		StringTokenizer st;
		String str;
		int a,b,c;
		while((str = br.readLine()) != null) {
			st	= new StringTokenizer(str);
			a	= Integer.parseInt(st.nextToken());
			b	= Integer.parseInt(st.nextToken());
			c	= Integer.parseInt(st.nextToken());
			sb.append(Math.max(b - a - 1, c - b - 1));
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}