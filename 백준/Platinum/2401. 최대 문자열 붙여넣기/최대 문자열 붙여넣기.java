//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2401
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	
	static String	text;
	static int		N, tlen;
	static int[]	DP, len;
	static boolean	match[][];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		text	= br.readLine();					// 긴 문자열(1<=십만)
		tlen	= text.length();
		N		= Integer.parseInt(br.readLine());	// 짧은 문자열의 개수(1<=500)
		len		= new int[N];
		match	= new boolean[N][text.length()];
		DP		= new int[tlen+1];
		
		
		for(int i=0; i<N; i++)
		{
			String pattern = br.readLine();			// 짧은 문자열을 배열에 저장
			len[i]		= pattern.length();			// 짧은 문자열의 길이를 저장
			int[] fail	= getFail(pattern, len[i]);	// 짧은 문자열의 fail값 저장
			KMP(pattern, i, fail);					// 구한 fail을 통해 바로 KMP알고리즘 실행
		}
		
        // 바텀업 방식으로 DP 계산
        for (int i = 0; i < tlen; i++)
        {
            DP[i + 1] = Math.max(DP[i + 1], DP[i]); // 현재 위치까지 최대값 유지

            for (int n = 0; n < N; n++)
            {
                if (match[n][i])
                {
                    int endIdx = i + len[n];
                    DP[endIdx] = Math.max(DP[endIdx], DP[i] + len[n]);
                }
            }
        }
		System.out.print(DP[tlen]);
	}
	
	public static void KMP(String pattern, int idx, int fail[]) {
		for(int i=0, j=0; i<tlen; i++)
		{
			while(0<j && text.charAt(i) != pattern.charAt(j))
				j = fail[j - 1];
			
			if(text.charAt(i) == pattern.charAt(j))
			{
				if(j == len[idx] - 1)
				{
					match[idx][i - j] = true;			// 해당 문자의 시작점에 true로 마킹
					j = fail[j];
				}
				else ++j;
			}
		}
	}
	public static int[] getFail(String pattern, int LEN) {
		int fail[] = new int[LEN];
		
		for(int i=1, j=0; i<LEN; i++)
		{
			while(0<j && pattern.charAt(i) != pattern.charAt(j))
				j = fail[j - 1];
			
			if(pattern.charAt(i) == pattern.charAt(j))
				fail[i] = ++j;
		}
		
		return fail;
	}
}

