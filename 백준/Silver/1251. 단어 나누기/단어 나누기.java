//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1251
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	static StringBuilder sb = new StringBuilder();
	static int len;
	static char word[];
	static String result;
	
	public static String read(int start, int end) {
		sb.setLength(0);
		while(end < start)
			sb.append(word[start--]);
		return sb.toString();
	}
	public static String concat(String...strings) {
		sb.setLength(0);
		for(String s : strings)
			sb.append(s);
		return sb.toString();
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		word	= br.readLine().toCharArray();
		len		= word.length;
		result	= "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz";
		for(int i=0;i<len-2; i++)
		{
			for(int j=i+1; j<len-1; j++)
			{
				String str = concat(read(i, -1), read(j, i), read(len-1, j));
				if(str.compareTo(result) < 0)
					result = str;
			}
		}
		System.out.print(result);
	}
}