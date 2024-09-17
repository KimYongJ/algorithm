//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/20444

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long N	= Integer.parseInt(st.nextToken());	// 1<=2의31제곱-1
		long K	= Long.parseLong(st.nextToken());	// 1<=2의61제곱-1
		
		long s = 0;
		long e = N;
		
		while(s <= e)
		{
			long mid	= (s + e) >> 1;				// 가로로 자르는 횟수
			long col	= N - mid;					// 세로로 자르는 횟수
			long total	= (mid + 1) * (col + 1);	// 총 잘린 종이 개수
			if(total == K)
			{
				System.out.print("YES");
				return;
			}
			if(total < K)
				s = mid + 1;
			else e = mid - 1;
		}
		System.out.print("NO");
	}
}

// 이하 완전 탐색..
//class Main{
//	public static void main(String[] args)throws Exception{
//		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		int N	= Integer.parseInt(st.nextToken());	// 1<=2의31제곱-1
//		long K	= Long.parseLong(st.nextToken());	// 1<=2의61제곱-1
//		List<Long> now = List.of(1L);
//		for(int i=1; i<=N; i++)
//		{
//			List<Long> next = new ArrayList<>();
//			for(int j=1; j<i; j++) {
//				next.add(now.get(j - 1) + j);
//			}
//			next.add(now.get(0) + i);
//			
//			now = next;
//		}
//		System.out.print(now.contains(K) ? "YES" : "NO");
//	}
//}