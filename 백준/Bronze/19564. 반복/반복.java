//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/19564
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str	= br.readLine();
		int len		= str.length();
		int K		= 1;
		for(int i=1; i<len; i++)
			if(str.charAt(i-1) >= str.charAt(i))
				K++;
		System.out.print(K);
	}
}