// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		PriorityQueue<Integer> m_bg = new PriorityQueue<>();
		PriorityQueue<Integer> m_sm = new PriorityQueue<>();
		PriorityQueue<Integer> f_bg = new PriorityQueue<>();
		PriorityQueue<Integer> f_sm = new PriorityQueue<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) // 남자 넣기 
		{
			int num = Integer.parseInt(st.nextToken());
			if(num < 0)	m_sm.add(-num);
			else m_bg.add(num);
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) // 여자 넣기 
		{
			int num = Integer.parseInt(st.nextToken());
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