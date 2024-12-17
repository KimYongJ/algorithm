//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/8913
//2초, 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
				sb.append(bruteforce(br.readLine()) ? 1 : 0)
					.append('\n');

		System.out.print(sb.toString());
	}
	public static boolean bruteforce(String str) {
		if(str.length() == 0)
			return true;
		if(str.length() == 1)
			return false;
		
		int cnt = 1;
		int len = str.length();
		
		for(int i=1; i<len; i++)
			if(str.charAt(i) == str.charAt(i-1))
				++cnt;
			else
			{
				if(2 <= cnt && bruteforce(str.substring(0,i-cnt) + str.substring(i, len)))
					return true;
				cnt = 1;
			}

		return cnt == str.length();
	}
}
