// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 총 문제 개수
		int L = Integer.parseInt(st.nextToken()); // 풀 수 있는 역량
		int K = Integer.parseInt(st.nextToken()); // 풀 문제 개수
		int result = 0, _100 = 0;
		int a,b;
		while(N-- > 0)
		{
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			if(b <= L) 
			{
				if(K > 0) 
				{
					result += 140;
					K--;
				}
			}
			else if(a <= L) 
			{
				_100++;
			}
		}
		
		K = Math.min(K, _100);
		
		System.out.print(result + 100 * K);
	}
}