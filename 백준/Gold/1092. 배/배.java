// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int C = Integer.parseInt(br.readLine())+1; // 크레인 개수
		int crr[] = new int[C]; // 크래인 값
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<C; i++) {
			crr[i] = Integer.parseInt(st.nextToken());
		}
		int B = Integer.parseInt(br.readLine()); // 박스 개수
		int brr[] = new int[B]; // 박스 값
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<B; i++) {
			brr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(brr); // 박스 값 오름차순
		Arrays.sort(crr); // 크레인 값 오름차순
		
		if(brr[B-1] > crr[C-1]) // 박스값이 커 크레인으로 못 옮기는 경우 종료 
		{
			System.out.print(-1);
			return;
		}
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();// 옮긴 횟수가 작은 것 순으로 오름차순
		int cidx = C-1;
		for(;cidx>0; cidx--) 
		{
			if(brr[B-1] > crr[cidx]) break;
			else pq.add(0);
		}

		for(int i=B-1; i>=0; i--) // 박스역방향 순회 
		{
			if(brr[i] > crr[cidx]) {
				pq.add(pq.poll()+1);
			}else {
				for(;cidx>0; cidx--) 
				{
					if(brr[i] > crr[cidx]) break;
					else pq.add(0);
				}
				pq.add(pq.poll()+1);
			}
		}
		
		int res = 0;
		while(!pq.isEmpty()) {
			res = Math.max(pq.poll(), res);
		}
		System.out.print(res);
	}
}