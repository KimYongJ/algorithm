// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Main{
	public static void main(String args[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 자리수
		int K = Integer.parseInt(st.nextToken()); // 지울 수
		int limit = N-K; // 최종 만들어야하는 길이
		int idx = 0;
		int origin[] = new int[N]; // 입력된 숫자를 배열로 변경
		
		for(char c : br.readLine().toCharArray())
			origin[idx++] = c-'0';

		Stack<Integer> stack = new Stack<>();
		
		for(int i=0; i<N; i++) {
			while(K != 0 && !stack.isEmpty() && stack.peek() < origin[i]) {
				stack.pop();
				K--;
			}
			stack.push(origin[i]);
		}
		while(stack.size() != limit) {
			stack.pop();
		}
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		
		System.out.print(sb.reverse().toString());
	}
}