//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/14926
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int ans = str.length();
		for(int i=0; i<str.length(); i++) {
			if(isPal(str.substring(i)))
				break;
			++ans;
		}
		System.out.print(ans);
	}
	public static boolean isPal(String str) {
		int l = -1;
		int r = str.length();
		while(++l<=--r) {
			if(str.charAt(l) != str.charAt(r))
				return false;
		}
		return true;
	}
}