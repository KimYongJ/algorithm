// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main{
	
	static int result = Integer.MIN_VALUE;
	static int N;
	static int DEPTH;
	static int order[];
	static ArrayList<Integer> numbers		= new ArrayList<>();// 숫자를 담을 배열
	static ArrayList<Character> operator	= new ArrayList<>();// 연산자를 담을 배열

	public static int cal(int a, int b, char op) {
		if(op == '+') return a+b;
		if(op == '-') return a-b;
		else return a*b;
	}
	public static void compare() {
		ArrayList<Integer> numc		= (ArrayList<Integer>) numbers.clone();
		ArrayList<Character> opc	= (ArrayList<Character>) operator.clone();
		// 우선인 연산자 연산
		for(int i=DEPTH-1; i>=0; i--) 
		{
			int idx = order[i];
			int num = cal(numc.get(idx), numc.get(idx+1), opc.get(idx));
			opc.remove(idx);
			numc.remove(idx);
			numc.remove(idx);
			numc.add(idx, num);
		}
		// 마이너스를 플러스로 변경하고 숫자를 음수로 바꾼다.
		for(int i=0; i<opc.size(); i++) {
			if(opc.get(i)== '-') {
				opc.remove(i);
				opc.add(i,'+');
				int num = -numc.get(i+1);
				numc.remove(i+1);
				numc.add(i+1, num);
			}
		}
		// 곱하기를 먼저 한다.
		for(int i=0; i<opc.size(); i++) {
			if(opc.get(i)== '*') {
				opc.remove(i);
				int num = numc.get(i) * numc.get(i+1);
				numc.remove(i);
				numc.remove(i);
				numc.add(i, num);
				i--;
			}
		}
		// 나머지를 더한다.
		int number = 0;
		for(int n : numc) {
			number += n;
		}
		if(result < number) {
			result = number;
		}
	}
	public static void DFS(int depth, int idx) {
		int size = operator.size();
		
		if(DEPTH == depth) {
			compare();
			return;
		}
		
		for(int i=idx; i<size; i++) {
			order[depth] = i;
			DFS(depth + 1, i+2);
		}
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		String arr = br.readLine();
		for(int i=0; i<N; i++) {
			if(i%2== 1) {
				operator.add(arr.charAt(i));
			}else {
				numbers.add(arr.charAt(i)-'0');
			}
		}
		
		if(N == 1) 
		{
			System.out.print(arr);
		}
		else if(N == 3) 
		{
			System.out.print(cal(numbers.get(0),numbers.get(1),operator.get(0)));
		}
		else 
		{
			int len = (int)Math.ceil(operator.size() / 2.0);
			for(DEPTH=1; DEPTH<=len; DEPTH++) 
			{
				order = new int[DEPTH];	// 먼저 연산할 순서를 담을 배열
				DFS(0,0);
			}
			System.out.print(result);
		}
	}
}