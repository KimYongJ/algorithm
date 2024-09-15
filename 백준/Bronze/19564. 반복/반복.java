//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/19564
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int len = str.length();
		int K = 1;
		char before = str.charAt(0);
		for(int i=1; i<len; i++)
		{
			char now = str.charAt(i);
			if(before >= now)
				K++;
			before = now;
		}
		
		System.out.print(K);
	}
}