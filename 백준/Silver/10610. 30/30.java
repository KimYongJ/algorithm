// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		char ch[]	= {'0','1','2','3','4','5','6','7','8','9'};
		int arr[]	= new int[10];
		String str	= br.readLine();
		int len		= str.length();
		int sum		= 0;
				
		for(int i=0; i<len; i++) 
		{
			int c = str.charAt(i)-'0';
			sum += c;
			arr[c]++;
		}
		
		if(arr[0] == 0 || sum % 3 != 0) 
		{
			bw.write("-1");
		}
		else 
		{
			for(int i=9; i>=0; i--) 
			{
				while(arr[i]-- > 0) 
				{
					bw.write(ch[i]);
				}
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}
}