//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2015
import java.util.HashMap;
class Main{
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) {
        n = (n << 3) + (n << 1) + (c & 15);}
        return m ? ~n + 1 : n;
    }
	public static void main(String[] args)throws Exception{
		HashMap<Long, Long> hm = new HashMap<>();
		long N		= read();;	// 값 개수 1<이십만
		long K		= read();;	// 목적 값|이십억|
		long ans	= 0L;
		long psum[]	= new long[(int)N + 1];

		for(int i=1; i<=N; i++)
		{
			psum[i] = psum[i-1] + read();
			
			if(psum[i] == K)
				++ans;
			
			ans += hm.getOrDefault(psum[i] - K, 0L);
			hm.put(psum[i], hm.getOrDefault(psum[i], 0L) + 1);
		}
		
		System.out.print(ans);
	}
}
