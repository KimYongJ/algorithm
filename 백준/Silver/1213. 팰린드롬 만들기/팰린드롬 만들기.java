// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		final char[] text = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb1 = new StringBuilder();
		StringBuilder	sb2 = new StringBuilder();
		String str	= br.readLine();
		int cnt[]	= new int[26];
		
		for(int i=0; i<str.length(); i++)
			cnt[str.charAt(i)-'A']++;
		
		int oddCnt		= 0;
		int oddIndex	= 0;
		
		for(int i=0; i<26; i++) 
		{
			if(cnt[i] % 2 == 1) 
			{
				oddCnt++;
				oddIndex = i;
				cnt[i]--;
			}
			cnt[i]/=2;
			while(cnt[i]-->0)
				sb1.append(text[i]);
		}

		if(oddCnt > 1) 
		{
			System.out.print("I'm Sorry Hansoo");
		}
		else 
		{
			sb2.append(sb1.toString()); // 백업
			
			if(oddCnt == 1)
				sb1.append(text[oddIndex]);
			
			sb1.append(sb2.reverse().toString());
			
			System.out.print(sb1.toString());
		}
	}
}
