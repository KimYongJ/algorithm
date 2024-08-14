// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Integer> list = new ArrayList<>();
		int N = Integer.parseInt(br.readLine());
		int origin1[] = new int[N];
		int max = 0;
		for(int i=0; i<N; i++) 
		{
			origin1[i] = Integer.parseInt(br.readLine());
			if(max < origin1[i]) {
				max = origin1[i];
			}
		}
		
		// 피보나치 수열 생성
		list.add(0);
		list.add(1);
		int idx = 2;
		while(list.get(idx-1) <= max) 
		{
			list.add(list.get(idx-1) + list.get(idx-2));
			idx++;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++)
		{
			PriorityQueue<Integer> pq = new PriorityQueue<>();
			int goal = origin1[i];
			
			for(int j=list.size()-1; j>=1; j--) {
				if(goal - list.get(j) >=0) {
					goal -= list.get(j);
					pq.add(list.get(j));
				}
				if(goal == 0)
					break;
			}
			while(!pq.isEmpty()) {
				sb.append(pq.poll()).append(' ');
			}
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}
