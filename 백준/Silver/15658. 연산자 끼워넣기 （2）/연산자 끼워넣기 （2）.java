// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, op[], number[];
	static int MAX, MIN;

	public static void DFS(int plus, int minus, int mul, int div, int depth, int sum) {
		if(depth == N) {
			if(sum > MAX) MAX = sum;
			if(sum < MIN) MIN = sum;
			return;
		}
		if(plus > 0) {
			DFS(plus-1, minus, mul, div, depth+1, sum + number[depth]);
		}
		if(minus > 0) {
			DFS(plus, minus-1, mul, div, depth+1, sum - number[depth]);
		}
		if(mul > 0) {
			DFS(plus, minus, mul-1, div, depth+1, sum * number[depth]);
		}
		if(div > 0) {
			DFS(plus, minus, mul, div-1, depth+1, sum / number[depth]);
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer	st;
		N = Integer.parseInt(br.readLine());
		number = new int[N];
		op	= new int[4];
		MAX = Integer.MIN_VALUE;
		MIN = Integer.MAX_VALUE;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			number[i] = Integer.parseInt(st.nextToken()); // 기준이 되는 숫자 삽입
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++)
			if((op[i]=Integer.parseInt(st.nextToken())) == N) // 연산자 개수 입력
				op[i] = N-1; // 연산자가 N을 초과하면 N-1개만큼 줄임
		
		DFS(op[0], op[1], op[2], op[3],1, number[0]);
		
		System.out.print(
				new StringBuilder().append(MAX).append('\n').append(MIN).toString()
				);
	}
}
