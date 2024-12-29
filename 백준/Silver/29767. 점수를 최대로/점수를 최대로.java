//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/29767
import java.util.PriorityQueue;

class Main{
	public static void main(String[] args)throws Exception{
		PriorityQueue<Long> pq = new PriorityQueue<>((a,b)->Long.compare(b, a));
		int N = read();	// 교실개수(1<=삼십만)
		int K = read();	// 학생수
		long sum[] = new long[N + 1];
		
		for(int i=1; i<=N; i++)
			pq.add(sum[i] += sum[i-1] + read());
		
		long res = 0;
		while(K-->0)
			res += pq.poll();

		System.out.print(res);
	}
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) {
        n = (n << 3) + (n << 1) + (c & 15);}
        return m ? ~n + 1 : n;
    }
}