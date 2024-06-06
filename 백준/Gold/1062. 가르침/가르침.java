// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int 		result;
	static int		bitArray[];	// 최종 비교할 문자열( 비트마스킹으로 숫자로 표현 )

	public static void combination(int depth, int idx, int bit) {
		if(depth == 0) 
		{
			int cnt = 0;
			for(int num : bitArray) 
				if((bit & num) == num)
					cnt++;
			if(result < cnt)
				result = cnt;
			return;
		}
		for(int i=idx; i<26; i++) 
		{
			int nb = 1<<i;
			if((bit & nb) == 0)
				combination(depth -1, i+1, bit | nb);
		}
		
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br 			= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer	st 			= new StringTokenizer(br.readLine());
		int N 		= Integer.parseInt(st.nextToken());
		int K 		= Integer.parseInt(st.nextToken());
		bitArray	= new int[N]; // 중복이 제거된, 입력되는 문자열을 bit로 바꾼거
		
		if(K >= 26) {
			result = N;
		}
		else if(K>=5) 
		{
			for(int i=0; i<N; i++) 
				for(char c : br.readLine().toCharArray())
					bitArray[i] |= 1 << (c-97);

			combination(K-5, 0, 532741); // 몇개를 고를건지, 인덱스, 현재 구해진 숫자
		}
		System.out.print(result);
	}
}