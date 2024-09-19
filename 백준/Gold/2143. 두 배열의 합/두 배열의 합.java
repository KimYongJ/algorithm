//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2143
import java.util.HashMap;
class Main{
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
        n = (n << 3) + (n << 1) + (c & 15);
        return m ? ~n + 1 : n;
    }
	public static void main(String[] args)throws Exception{
		long cnt		= 0;
		long T			= read();		// -십억<=십억
		int N			= read();		// 1<=천
		long arr1[]		= new long[N];	// -백만<=백만
		for(int i=0; i<N; i++)
			arr1[i] = read();
		
		int M			= read();
		long arr2[]		= new long[M];	// -백만<=백만
		for(int i=0; i<M; i++)
			arr2[i] = read();
		
		HashMap<Long, Long> hm = new HashMap<>();
		for(int i=0; i<N; i++)
		{
			long sum = 0;
			for(int j=i; j<N; j++)
				hm.put(sum += arr1[j], hm.getOrDefault(sum, 0L) + 1);
		}
		
		for(int i=0; i<M; i++)
		{
			long sum = 0;
			for(int j=i; j<M; j++)
				cnt += hm.getOrDefault(T - (sum += arr2[j]), 0L);
		}
		
		System.out.print(cnt);
	}
}
/*
10
1
9
5
1 -1 -2 3 5
출 3
 * */