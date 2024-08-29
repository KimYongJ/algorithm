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
			
			HashSet<Integer> dummy = new HashSet<>(cord);// 연산을 위한 복사
			for(int i= idx+1; i<K && dummy.size() > 1; i++)// 현재 코드에 있는게 하나남을 때 까지 반복
				dummy.remove(arr[i]);// 가장 늦게 사용되는것을 체크하기 위한 것
			
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
/*
3 100
56 71 70 25 52 77 76 8 68 71 51 65 13 23 7 16 19 54 95 18 86 74 29 76 61 93 44 96 32 72 64 19 50 49 22 14 7 64 24 83 6 3 2 76 99 7 76 100 60 60 6 50 90 49 27 51 37 61 16 84 89 51 73 28 90 77 73 39 78 96 78 13 92 54 70 69 62 78 7 75 30 67 97 98 19 86 90 90 2 39 41 58 57 84 19 8 52 39 26 7
answer - 80
 * */
