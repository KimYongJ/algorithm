// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) 
			pq.add(Integer.parseInt(st.nextToken()));

		int sum	= 0;
		int cnt = 0;
		for(int i=0; i<N; i++) 
		{
			sum += pq.poll();	// 지금까지 만든 체인개수
			cnt = N - 1 - i;	// 특정 인덱스일 때 남은 체인 개수
			if(sum >= cnt) {
				break;
			}
		}
		System.out.print(cnt);
	}
}