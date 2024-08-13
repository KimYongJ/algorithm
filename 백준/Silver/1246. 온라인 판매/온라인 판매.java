// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a,b)->b-a);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 달걀 개수
		int M = Integer.parseInt(st.nextToken()); // 고객수
		
		for(int i=0; i<M; i++)
			pq.add(Integer.parseInt(br.readLine()));
		
		int price = 0;
		int cnt = 0;
		for(int i=1; i<=M && i<= N; i++) {
			int nowPrice = pq.poll();
			if(price*cnt < nowPrice*i) {
				price = nowPrice;
				cnt = i;
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(price).append(' ').append(price * cnt);
		System.out.print(sb.toString());
	}
}
