// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str	= br.readLine();
		int len		= str.length();
		int _0		= 0;
		int _1		= 0;
		char before	= '-';
		char now;
		for(int i=0; i<len; i++) 
		{
			now = str.charAt(i);
			if(before != now) 
			{
				before = now;
				if(before == '0') 
				{
					_0++;
				}
				else 
				{
					_1++;
				}
			}
		}
		if(_0 == 0 || _1 == 0) 
		{
			System.out.print(0);
		}
		else
		{
			System.out.print(Math.min(_0, _1));
		}
	}
}