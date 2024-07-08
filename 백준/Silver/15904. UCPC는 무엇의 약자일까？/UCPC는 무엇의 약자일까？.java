// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char arr[]	= {'U','C','P','C'};
		String str	= br.readLine();
		int len		= str.length();
		int idx		= 0;
		
		for(int i=0; i<len ; i++) 
		{
			if(str.charAt(i) == arr[idx]) 
			{
				if(++idx == 4) 
				{
					System.out.print("I love UCPC");
					return;
				}
			}
		}
		System.out.print("I hate UCPC");
	}
}