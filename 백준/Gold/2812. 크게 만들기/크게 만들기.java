// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Main{
	public static void main(String args[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 자리수
		int K = Integer.parseInt(st.nextToken()); // 지울 수
		int limit = N-K; // 최종 만들어야하는 길이
		String str = br.readLine();
		ArrayDeque<Character> dq = new ArrayDeque<>();

		
		for(int i=0; i<N; i++) {
			while(K != 0 && !dq.isEmpty() && dq.peekLast() < str.charAt(i)) 
			{
				dq.pollLast();
				if(--K==0)break;
			}
			dq.addLast(str.charAt(i));
		}
		
		while(limit-->0)
			sb.append(dq.poll());
		
		
		System.out.print(sb.toString());
	}
} 