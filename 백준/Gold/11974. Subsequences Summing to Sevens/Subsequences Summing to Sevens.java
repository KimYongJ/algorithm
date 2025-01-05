//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11974
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
class Main{
	public static void main(String[] args)throws Exception{
		HashMap<Long, Integer> hm = new HashMap<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		long sum	= 0;
		long mod	= 0;
		long max	= 0;
		
		hm.put(0L, 0);
		
		for(int i=1; i<=N; i++)
		{
			sum += Integer.parseInt(br.readLine());
			mod = sum %7;
			
			if(hm.containsKey(mod))
			{
				int s = hm.get(mod);
				max = Math.max(max, i - s);
			}
			else hm.put(mod, i);
		}
		System.out.print(max);
	}
}