// https://github.com/kimyongj/algorithm
import java.util.Arrays;
import java.util.PriorityQueue;
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int C		= read()+1; 	// 크레인 개수, +1을 해주어 0을 삽입함으로 추후 연산을 편하게함
		int crr[]	= new int[C];	// 크래인 값
		for(int i=1; i<C; i++)
			crr[i] = read();
		
		int B		= read();		// 박스 개수
		int brr[]	= new int[B];	// 박스 값
		for(int i=0; i<B; i++)
			brr[i] = read();
		
		Arrays.sort(brr); // 박스 값 오름차순
		Arrays.sort(crr); // 크레인 값 오름차순
		
		if(brr[B-1] > crr[C-1]) // 박스값이 커 크레인으로 못 옮기는 경우 종료 
		{
			System.out.print(-1);
			return;
		}
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();// 총 옮긴 횟수가 작은 크레인 순으로 오름차순
		int cidx = C-1; // 크레인 인덱스
		int res = 0;	// 결과
		for(;cidx>0; cidx--) // 크레인 인덱스 세팅
		{
			if(brr[B-1] > crr[cidx]) break;
			else pq.add(0);
		}

		for(int i=B-1; i>=0; i--) // 박스역방향 순회 
		{
			if(brr[i] <= crr[cidx]) // 크레인 인덱스 세팅
			{
				for(;cidx>0; cidx--) 
				{
					if(brr[i] > crr[cidx]) break;
					else pq.add(0);
				}
			}
			int n = pq.poll() + 1;
			if(res < n)
				res = n;
			pq.add(n);
		}
		System.out.print(res);
	}
}