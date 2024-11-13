//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1251
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static String get(int s, int e, String o) {
		return new StringBuilder(o.substring(s,e)).reverse().toString();
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		String result	= "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz";
		String origin	= br.readLine();
		int len			= origin.length();
		
		for(int i=0;i<len-2; i++)
			for(int j=i+1; j<len-1; j++)
			{
				String str = new StringBuilder().append(get(0,i+1, origin)).append(get(i+1,j+1, origin)).append(get(j+1, len, origin)).toString();
				if(str.compareTo(result) < 0)
					result = str;
			}
		
		System.out.print(result);
	}
}