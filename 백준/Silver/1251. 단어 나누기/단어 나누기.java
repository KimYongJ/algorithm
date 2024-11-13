//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1251
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		String result	= "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz";
		String origin	= br.readLine();
		int len			= origin.length();
		
		for(int i=0;i<len-2; i++)
		{
			for(int j=i+1; j<len-1; j++)
			{
				String s1	= new StringBuilder(origin.substring(0,i+1)).reverse().toString();
				String s2	= new StringBuilder(origin.substring(i+1,j+1)).reverse().toString();
				String s3	= new StringBuilder(origin.substring(j+1,len)).reverse().toString();
				String str	= new StringBuilder().append(s1).append(s2).append(s3).toString();
				if(str.compareTo(result) < 0)
					result = str;
			}
		}
		System.out.print(result);
	}
}