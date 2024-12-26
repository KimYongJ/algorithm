//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2015
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long N		= Integer.parseInt(st.nextToken());	// 값 개수 1<이십만
		long K		= Integer.parseInt(st.nextToken());	// 목적 값|이십억|
		long psum[]	= new long[(int)N + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			psum[i] = psum[i-1] + Integer.parseInt(st.nextToken());
		
		long ans = 0;
		HashMap<Long, Long> hm = new HashMap<>();
		
		for(int i=1; i<=N; i++)
		{
			if(psum[i] == K) ++ ans;
			ans += hm.getOrDefault(psum[i] - K, 0L);
			hm.put(psum[i], hm.getOrDefault(psum[i],0L) + 1);
		}
		System.out.print(ans);
	}
}