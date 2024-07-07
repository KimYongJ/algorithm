// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder	sb = new StringBuilder();
		int L = Integer.parseInt(st.nextToken()); // 캠핑장 사용 가능 일수
		int P = Integer.parseInt(st.nextToken()); // 캠핑장에 연속으로 있을 수 있는 날
		int V = Integer.parseInt(st.nextToken()); // 총 휴일 개수
		int idx = 1;
		while(L != 0 || P != 0 || V != 0) 
		{
			int result = ((V / P)*L) + Math.min(V % P,L);
			
			sb.append("Case ").append(idx++)
				.append(": ").append(result)
				.append('\n');
			
			st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken()); // 사용 가능 일수
			P = Integer.parseInt(st.nextToken()); // 캠핑장에 연속으로 있을 수 있는 날
			V = Integer.parseInt(st.nextToken()); // 총 휴일 개수
		}
		System.out.print(sb);
	}
}