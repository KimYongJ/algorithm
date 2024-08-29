// https://github.com/kimyongj/algorithm
// https://www.acmicpc.net/problem/1700
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); 	// 멀티탭 구멍(1<=100)
		int K = Integer.parseInt(st.nextToken()); 	// 전기 용품 사용회수(1<=100)
		int result = 0;							  	// 결과값
		int arr[] = new int[K];
		int cnt[] = new int[101];					// 특정 기구가 사용되는 횟수
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<K; i++) 
		{
			arr[i] = Integer.parseInt(st.nextToken());
			++cnt[arr[i]];
		}
		
		Set<Integer> cord = new HashSet<>();		// 멀티탭 표현
		int idx = 0;
		while(cord.size() != N && idx < K) 
		{
			--cnt[arr[idx]];
			cord.add(arr[idx++]);					// 멀티탭에 기구를 넣는 것
		}
		
		// 한번에 다 넣을 수 있다면 조기종료
		if(K <= idx) {
			System.out.print(0);
			return;
		}
		
		for(; idx<K; idx++) 
		{
			--cnt[arr[idx]];
			if(cord.contains(arr[idx])) // 이미 코드에 있을 경우 
			{
				continue;
			}
			
			result++;
			
			boolean isContinue = true;
			for(int now : cord) // 현재 코드에서 한번도 앞으로 한번도 사용되지 않을 것을 체크
			{
				if(cnt[now] == 0) {
					cord.remove(now);
					cord.add(arr[idx]);
					isContinue = false;
					break;
				}
			}
			if(isContinue) // 현재 코드에 있는게 나중에 모두 사용된다면, 그나마 가장 늦게 사용되는 것을 탐색
			{
				Set<Integer> dummy = new HashSet<Integer>(cord);
				for(int i= idx+1; i<K && dummy.size() > 1; i++)
					if(dummy.contains(arr[i]))
						dummy.remove(arr[i]);
				
				for(int n : dummy) {
					cord.remove(n);
					cord.add(arr[idx]);
					break;
				}
			}
		}
		
		
		System.out.print(result);
	}
}