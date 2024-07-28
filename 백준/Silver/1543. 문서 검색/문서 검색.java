//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		String str2 = br.readLine();
		int len1 = str1.length();
		int len2 = str2.length();
		int cnt = 0;
		for(int i=0; i<=len1-len2; i++) {
			if(str1.substring(i,i+len2).equals(str2)) {
				cnt++;
				i += len2-1;
			}
		}
		System.out.print(cnt);
	}
}