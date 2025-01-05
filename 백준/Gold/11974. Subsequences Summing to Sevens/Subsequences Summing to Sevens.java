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
		long arr[]	= new long[N+1];
		long mod[]	= new long[N+1];
		long max	= 0;
		hm.put(0L, 0);
		for(int i=1; i<=N; i++)
		{
			arr[i] += arr[i-1] + Integer.parseInt(br.readLine());
			mod[i] = arr[i] %7;
			
			if(hm.containsKey(mod[i]))
			{
				int s = hm.get(mod[i]);
				max = Math.max(max, i - s);
			}
			else hm.put(mod[i], i);
		}
		System.out.print(max);
	}
}