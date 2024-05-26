// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int 	result = -1;
	static int	N, song, maxCnt; 
	static long list[]; // 각 기타에 따른 연주 가능 곡의 정보, bit로 표현
	
	public static int getCnt(long sum) {
		long comp = 1;
		int cnt = 0;
		for(int i=0; i<64; i++)
			if((sum & comp<<i) != 0)
				cnt++;
		
		return cnt;
	}
	public static void comb(int base, int depth, int idx, long sum) {
		if(depth == 0) {
			int cnt = getCnt(sum);
			if(maxCnt < cnt) {
				maxCnt = cnt;
				result = base;
			}
			return;
		}
		
		for(int i=idx; i<N; i++) {
			if(list[i] != 0)
				comb(base, depth-1, i+1, sum | list[i]);
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N 		= Integer.parseInt(st.nextToken());
		song 	= Integer.parseInt(st.nextToken());
		list 	= new long[N];
		
		for(int i=0; i<N; i++) 
		{
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			String str = st.nextToken();
			
			for(int j=0; j<str.length(); j++)
				if(str.charAt(j)=='Y')
					list[i] |= 1L<<j;
		}
		
		// 조합을 구한다. 1개부터 N개까지.
		for(int i=1; i<=N; i++)
			comb(i,i,0,0);
		
		System.out.print(result);
	}
}