//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11974
import java.util.HashMap;
class Main{
	public static void main(String[] args)throws Exception{
		HashMap<Long, Integer> hm = new HashMap<>();
		int N		= read();
		long sum	= 0;
		long mod	= 0;
		long max	= 0;
		
		hm.put(0L, 0);
		
		for(int i=1; i<=N; i++)
		{
			sum += read();
			mod = sum %7;
			
			if(hm.containsKey(mod))
				max = Math.max(max, i - hm.get(mod));
			else
				hm.put(mod, i);
		}
		System.out.print(max);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}