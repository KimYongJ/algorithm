//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/5618
import java.util.TreeSet;
class Main{	
	public static void main(String[] args)throws Exception{
		StringBuilder	sb = new StringBuilder();
		int n	= read();
		int GCD = GCD(read(), read());
		if(n == 3)
			GCD = GCD(GCD, read());
		
		TreeSet<Integer> set = new TreeSet<>();
		for(int i=1; i*i<=GCD; i++)
			if(GCD % i == 0)
			{
				set.add(i);
				set.add(GCD / i);
			}
		
		for(int s : set)
			sb.append(s).append('\n');
		
		System.out.print(sb);
	}
	public static int GCD(int a, int b) {
		return b == 0 ? a : GCD(b, a%b);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}