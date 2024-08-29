// https://github.com/kimyongj/algorithm
// https://www.acmicpc.net/problem/1700
import java.util.HashSet;
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();	// 멀티탭 구멍(1<=100)
		int K		= read();	// 전기 용품 사용회수(1<=100)
		int result	= 0;		// 결과값
		int arr[]	= new int[K];
		
		for(int i=0; i<K; i++) 
			arr[i] = read();
		
		HashSet<Integer> cord = new HashSet<>();	// 멀티탭 표현
		
		int idx = 0;
		while(cord.size() != N && idx < K) 
			cord.add(arr[idx++]);					// 멀티탭에 기구를 넣는 것
		
		// 한번에 다 넣을 수 있다면 조기종료
		if(K <= idx) 
		{
			System.out.print(0);
			return;
		}
		// 그리디 원리 : 현재 코드에 있는 것들 중 가장 늦게 사용되는 것을 체크
		for(; idx<K; idx++) 
		{
			if(cord.contains(arr[idx])) // 이미 코드에 있을 경우 
				continue;

			result++;
			
			HashSet<Integer> dummy = new HashSet<Integer>(cord);// 연산을 위한 복사
			for(int i= idx+1; i<K && dummy.size() > 1; i++)// 현재 코드에 있는게 하나남을 때 까지 반복
				if(dummy.contains(arr[i]))// 가장 늦게 사용되는것을 체크하기 위한 것
					dummy.remove(arr[i]);
			
			for(int n : dummy)
			{
				cord.remove(n);
				cord.add(arr[idx]);
				break;
			}
		}
		System.out.print(result);
	}
}