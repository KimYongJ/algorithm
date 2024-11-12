//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1145
class Main{
	
	static int min	= 1<<30;
	static int num[]= new int[5];
	
	public static void main(String[] args)throws Exception{
		for(int i=0; i<5; i++)
			num[i] = read();
		
		bruteforce(0, 0, 1);
		
		System.out.print(min);
	}
	public static void bruteforce(int idx, int depth, int lcm) {
		if(depth == 3)
		{
			min = Math.min(min, lcm);
			return;
		}
		if(min < lcm || 5 == idx)
			return;
		
		bruteforce(idx + 1, depth + 1, LCM(lcm, num[idx]));
		bruteforce(idx + 1, depth, lcm);

	}
	public static int LCM(int a, int b)
	{return a*b / GCD(a,b);}
	public static int GCD(int a, int b)
	{return b == 0 ? a : GCD(b, a % b);}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}