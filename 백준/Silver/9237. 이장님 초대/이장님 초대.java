// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a,b)->b-a);
		int N	= Integer.parseInt(br.readLine());
		int max = 0;
		int day = 1;
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		while(N-->0)
			pq.add(Integer.parseInt(st.nextToken()));
		
		while(!pq.isEmpty()) 
		{
			max = Math.max(max, day + pq.poll());
			day++;
		}
		System.out.print(max + 1); // 이장 초대는 하루 뒤이므로
	}
}