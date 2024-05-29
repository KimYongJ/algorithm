// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int result = 1;
		for(int i=0,j=str.length()-1; i<j; i++,j--)
			if(str.charAt(i) != str.charAt(j)) 
			{
				result = 0;
				break;
			}
		
		System.out.print(result);
	}
}