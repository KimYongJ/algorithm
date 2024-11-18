//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/2075
import java.util.PriorityQueue;
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
		PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a);
		int N = read();
		
		for(int y=0; y<N; y++)
			for(int x=0; x<N; x++)
				pq.add(read());
		
		while(--N>0)
			pq.poll();
		
		System.out.print(pq.poll());
	}
}