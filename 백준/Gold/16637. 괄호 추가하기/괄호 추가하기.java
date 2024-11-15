//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/16637
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	
	static int max = ~(1<<30);
	static int N, len, num[];
	static char op[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N	= Integer.parseInt(br.readLine());	// 수식의 길이 1<=19
		len	= N/2;
		num	= new int[len+1];
		op	= new char[len];
		int idx		= 0;
		String str	= br.readLine();
		for(int i=0; i<len; i++)
		{
			num[i]	= str.charAt(idx++) - '0';
			op[i]	= str.charAt(idx++);
		}
		num[len] = str.charAt(idx) - '0';
		
		DFS(0,num[0]);
		
		System.out.print(max);
	}
	public static void DFS(int idx, int sum) {
		if(len <= idx)
		{
			max = Math.max(max, sum);
			return;
		}
		
		DFS(idx + 1, cal(op[idx], sum, num[idx+1]));
		
		if(idx + 2 <= len) {
			int nextSum = cal(op[idx+1], num[idx+1], num[idx+2]);
			DFS(idx + 2, cal(op[idx], sum ,nextSum));
		}
	}
	public static int cal(char op, int l, int r) {
		switch(op)
		{
		case '+': return l+r;
		case '-': return l-r;
		default : return l*r;
		}
	}
}