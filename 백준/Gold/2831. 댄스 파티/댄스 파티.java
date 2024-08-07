// https://github.com/kimyongj/algorithm
import java.util.PriorityQueue;
class Main{
	public static int read() throws Exception {
		int c, n = System.in.read() & 15;
		boolean isNegative = n == 13;
		if (isNegative) n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13) System.in.read();
		return isNegative ? ~n + 1 : n;
	}
	public static void main(String[] args)throws Exception{
		PriorityQueue<Integer> m_bg = new PriorityQueue<>();
		PriorityQueue<Integer> m_sm = new PriorityQueue<>();
		PriorityQueue<Integer> f_bg = new PriorityQueue<>();
		PriorityQueue<Integer> f_sm = new PriorityQueue<>();
		int N = read();
		for(int i=0; i<N; i++) // 남자 넣기 
		{
			int num = read();
			if(num < 0)	m_sm.add(-num);
			else m_bg.add(num);
		}
		for(int i=0; i<N; i++) // 여자 넣기 
		{
			int num = read();
			if(num < 0)	f_sm.add(-num);
			else f_bg.add(num);
		}
		int cnt = 0;
		while(!(m_bg.isEmpty() || f_sm.isEmpty())) 
			if(m_bg.peek() < f_sm.poll()) {
				cnt++;
				m_bg.poll();
			}

		while(!(m_sm.isEmpty() || f_bg.isEmpty())) 
			if(f_bg.peek() < m_sm.poll()) 
			{
				cnt++;
				f_bg.poll();
			}
		
		System.out.print(cnt);
	}
}
