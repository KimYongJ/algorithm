// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str	= br.readLine();
		int len		= str.length();
		int sum		= 0;
		int arr[]	= new int[10];
		
		for(int i=0; i<len; i++) 
		{
			int c = str.charAt(i)-'0';
			sum += c;
			arr[c]++;
		}
		
		if(arr[0] == 0 || sum % 3 != 0) 
		{
			System.out.print(-1);
		}
		else 
		{
			StringBuilder sb = new StringBuilder();
			for(int i=9; i>=0; i--) 
			{
				while(arr[i]-- > 0) 
				{
					sb.append(i);
				}
			}
			System.out.print(sb.toString());
		}
	}
}