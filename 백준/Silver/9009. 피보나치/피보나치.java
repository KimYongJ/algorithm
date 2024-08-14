// https://github.com/kimyongj/algorithm
import java.util.ArrayList;
import java.util.PriorityQueue;
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		ArrayList<Integer> list = new ArrayList<>();
		int N			= read();
		int origin1[]	= new int[N];
		int max			= 0;
		
		for(int i=0; i<N; i++) 
			if(max < (origin1[i]= read()))
				max = origin1[i];
		
		// 피보나치 수열 생성
		list.add(0);
		list.add(1);
		int idx = 1;
		while(list.get(++idx-1) <= max) 
			list.add(list.get(idx-1) + list.get(idx-2));
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++)
		{
			PriorityQueue<Integer> pq = new PriorityQueue<>();
			for(int j=list.size()-1; j>=1 && origin1[i] != 0; j--)
				if(origin1[i] - list.get(j) >=0)
				{
					origin1[i] -= list.get(j);
					pq.add(list.get(j));
				}

			while(!pq.isEmpty())
				sb.append(pq.poll()).append(' ');
			
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}