//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/16637
class Main{
	
	static int max = ~(1<<30);
	static int N, len, num[], op[];
	
	public static void main(String[] args)throws Exception{
		N	= read();	// 수식의 길이 1<=19
		len	= N/2;
		num	= new int[len+1];
		op	= new int[len];
		
		for(int i=0; i<len; i++)
		{
			num[i]	= System.in.read() - '0';
			op[i]	= (char)System.in.read();
		}
		num[len] = System.in.read() - '0';
		
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
	public static int cal(int op, int l, int r) {
		switch(op)
		{
		case '+': return l+r;
		case '-': return l-r;
		default : return l*r;
		}
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}