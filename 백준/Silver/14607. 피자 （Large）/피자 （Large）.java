//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14607
//1초 / 512mb
import java.util.HashMap;
class Main{
	
	static HashMap<Long , Long> map = new HashMap<>();
	
	public static void main(String[] args)throws Exception{
		System.out.print(DFS(read()));	// 1<=십억
	}
	public static long DFS(long n) {
		if(n == 1)
			return 0;
		
		if(map.containsKey(n))
			return map.get(n);
		
		long left	= n/2;
		long right	= n - left;
		long res	= left * right + DFS(left) + DFS(right); 
		
		map.put(n, res);
		return res;
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}