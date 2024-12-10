//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1097
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	
	static int ans;
	static int N, K;
	static String str[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N	= Integer.parseInt(br.readLine());	// 단어의 개수 N(1<8)
		str	= new String[N];
		
		for(int i=0; i<N; i++)
			str[i] = br.readLine();// 최대길이 20 문자열
		
		K = Integer.parseInt(br.readLine());	// 1<=200

		bruteforce(0, 0, "");
		
		System.out.print(ans);
	}
	public static void bruteforce(int depth, int bitmask, String text) {
		if(depth == N)
		{
			KMP(text + text, text);
			return;
		}
		for(int i=0; i<N; i++)
		{
			int flag = 1<<i;
			if((bitmask & flag) == 0)
				bruteforce(depth + 1, bitmask | flag, text + str[i]);
		}
	}
	public static void KMP(String text, String pattern) {
		int plen	= pattern.length();
		int fail[]	= new int[plen];
		// fail 배열 구하기
		for(int i=1,j=0; i<plen; i++)
		{
			while(0<j && pattern.charAt(i) != pattern.charAt(j))
				j = fail[j - 1];
			
			if(pattern.charAt(i) == pattern.charAt(j))
				fail[i] = ++j;
		}
		// KMP알고리즘
		int tlen = text.length() - 1;
		int cnt = 0;
		for(int i=0, j=0; i<tlen; i++)
		{
			while(0<j && text.charAt(i) != pattern.charAt(j))
				j = fail[j - 1];
			
			if(text.charAt(i) == pattern.charAt(j))
			{
				if(j+1 == plen)
				{
					++cnt;
					j = fail[j];
				}
				else ++j;
			}
		}
		
		if(cnt == K)
			++ans;
	}
}