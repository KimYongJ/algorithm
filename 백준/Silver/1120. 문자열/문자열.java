//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1120
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String s1	= st.nextToken();
		String s2	= st.nextToken();
		int LEN		= s2.length() - s1.length();
		int MIN		= Integer.MAX_VALUE;
		
		for(int i=0, cnt = 0; i<=LEN && MIN != 0; i++, cnt = 0)
		{
			for(int j=0; j<s1.length(); j++)
				if(s1.charAt(j) != s2.charAt(i + j))
					++cnt;
			MIN = Math.min(cnt, MIN);
		}
		System.out.print(MIN);
	}
}