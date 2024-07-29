// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb1 = new StringBuilder(br.readLine());
		StringBuilder sb2 = new StringBuilder(br.readLine());
		while(sb1.length() < sb2.length()) {
			char c = sb2.charAt(sb2.length()-1);
			sb2.deleteCharAt(sb2.length()-1);
			if(c == 'B')	
				sb2 = sb2.reverse();
			else if(c != 'A') {
				sb2.append(c);
				break;
			}
		}
		System.out.print(sb1.toString().equals(sb2.toString()) ? 1 : 0);
	}
}