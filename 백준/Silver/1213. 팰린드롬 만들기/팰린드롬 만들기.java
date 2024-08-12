// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb1 = new StringBuilder();
		StringBuilder	sb2 = new StringBuilder();
		int cnt[] = new int[26];
		String str = br.readLine();
		
		for(int i=0; i<str.length(); i++)
			cnt[str.charAt(i)-'A']++;
		
		int oddCnt = 0;
		int oddIndex = -1;
		for(int i=0; i<26; i++) 
		{
			if(cnt[i] % 2 == 1) 
			{
				oddCnt++;
				oddIndex = i;
				cnt[i]--;
			}
			cnt[i]/=2;
		}

		if(oddCnt > 1) 
		{
			System.out.print("I'm Sorry Hansoo");
		}
		else 
		{
			for(int i=0; i<26; i++)
				while(cnt[i]-->0)
					sb1.append((char)(i+'A'));

			sb2.append(sb1.toString()); // 백업
			
			if(oddCnt == 1)
			{
				sb1.append((char)(oddIndex+'A'));
			}
			
			sb1.append(sb2.reverse().toString());
			
			System.out.print(sb1.toString());
		}
	}
}
