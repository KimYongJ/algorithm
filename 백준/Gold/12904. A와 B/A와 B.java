// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String base = br.readLine();
		StringBuilder sb = new StringBuilder(br.readLine());
		while(base.length() != sb.length()) {
			char c = sb.charAt(sb.length()-1);
			sb.deleteCharAt(sb.length()-1);
			if(c == 'B')	sb = sb.reverse();
			else if(c != 'A') {
				sb.append(c);
				break;
			}
		}
		System.out.print(base.equals(sb.toString()) ? 1 : 0);
	}
}