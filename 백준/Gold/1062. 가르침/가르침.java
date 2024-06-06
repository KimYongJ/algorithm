// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main{
	
	static int 		result;
	static int 		N, K;
	static int 		len, arr[];
	static int		bitArray[];	// 최종 비교할 문자열( 비트마스킹으로 숫자로 표현 )
	
	public static void check(int bit) {
		int cnt = 0;
		for(int num : bitArray) 
		{
			if(num == 0) 
				cnt++;
			else if((bit & num) == num)
				cnt++;
		}
		if(result < cnt)
			result = cnt;
	}
	public static void combination(int depth, int idx, int bit) {
		if(depth == 0) 
		{
			check(bit);
			return;
		}
		for(int i=idx; i<len; i++)
			combination(depth -1, i+1, bit | (1<<arr[i]));
		
	}
	public static void main(String[] args)throws Exception{
		HashSet<Integer> chList 	= new HashSet<>();		// 조합의 기준이 되는 문자열들 ( 비트마스킹 표시할 거라 int로 변환 )
		boolean visit[] 			= new boolean[26];		// 이미 사용된 값을 체크하기 위한것
		BufferedReader 	br 			= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer	st 			= new StringTokenizer(br.readLine());
		
		N 		= Integer.parseInt(st.nextToken());
		K 		= Integer.parseInt(st.nextToken());
		bitArray= new int[N]; // 중복이 제거된, 입력되는 문자열을 bit로 바꾼거
		
		visit[0] = visit[2] = visit[8] = visit[13] = visit[19] = true; // a,c,i,n,t 방문처리
		
		if(K >= 26) {
			result = N;
		}
		else if(K>=5) 
		{
			for(int i=0; i<N; i++) 
			{
				String str = br.readLine();
				int num = 0;
				int len = str.length()-4;
				for(int j=4; j<len; j++) 
				{
					int c = str.charAt(j)-'a';
					if(!visit[c])
					{
						num |= 1 << (c);
						chList.add(c);
					}
				}
				bitArray[i] = num; // 최종 대조할 문자열 리스트( 비트마스킹으로 숫자로 표현 )
			}
			
			arr = chList.stream().mapToInt(Integer::intValue).toArray();
			len = arr.length;
			combination(Math.min(K-5,len), 0, 0); // 몇개를 고를건지, 인덱스, 현재 구해진 숫자
		}
		System.out.print(result);
	}
}