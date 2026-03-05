//https://www.acmicpc.net/problem/24830
//1초 1024MB
//덧셈 : 두 숫자 더한 후 이전 연산의 결과를 뺌
//뺄셈 : 두 숫자를 뺀후 이전 연산에 곱
//곱셈 : 두 숫자를 곱한 후 그 결과를 제곱
//나눗셈 : 첫수가 짝수면 2로 나누고, 아닐 경우 첫번째 숫자에 1을 더한 후 2를 나눔
//초기값 : 1
//5 // 명령 수 1<=1,000
//4 * 5 // 숫자와 명령어 0<=100,000
//2 + 5
//3 - 1
//20 / 3
//13 / 24
//답
//400
//-393
//-786
//10
//7
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		long num = 1;
		int N = Integer.parseInt(br.readLine());
		
		while(N-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			long a = Long.parseLong(st.nextToken());
			char c = st.nextToken().charAt(0);
			long b = Long.parseLong(st.nextToken());
			
			if(c == '+')
				num = a + b - num;
			else if(c == '-')
				num *= a - b;
			else if(c == '*')
				num = (a*b) * (a*b);
			else
				num = (a + 1) / 2;
			
			sb.append(num).append('\n');
		}
		System.out.print(sb);
	}
}