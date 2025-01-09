//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1644
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N			= Integer.parseInt(br.readLine());
		int cnt			= 0;
		int len			= 0;
		int list[]		= new int[283_147];
		boolean visit[] = new boolean[N+1];
		
		for(int i=2; i<=N; i++)
			if(!visit[i])
			{
				list[len++] = i;			// 소수를 list에 담는다. len은  list의최대길이가됨
				for(int j=i; j<=N; j+=i)
					visit[j] = true;
			}
		
		if(2<=N)
		{
			int l	= 0;
			int r	= 0;
			int sum = 2;
			while(r<len)
			{
				if(sum <= N)
				{
					if(sum==N)
						++cnt;

					sum += list[++r];
				}
				else
					sum -= list[l++];
			}
		}
		System.out.print(cnt);
	}
}
/////////////// 이하 누적합 풀이
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//class Main{
//	public static void main(String[] args)throws Exception{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		HashMap<Long,Integer> map	= new HashMap<>();
//		ArrayList<Integer> list		= new ArrayList<>();
//		
//		int N			= Integer.parseInt(br.readLine());
//		boolean visit[] = new boolean[N+1];
//		
//		for(int i=2; i<=N; i++)
//			if(!visit[i])
//			{
//				list.add(i);
//				for(int j=i; j<=N; j+=i)
//					visit[j] = true;
//			}
//		
//		int len		= list.size();
//		long psum[]	= new long[len + 1];
//		for(int i=0; i<len; i++)
//			psum[i+1] = list.get(i) + psum[i];
//		
//		
//		// [누적합 공식]
//		//  목적값 = 현재누적합 - 과거누적합 => 현재누적합 - 목적값 = 과거누적합이 되므로, 
//		// 과거 누적합의 개수에 대해서 플러스해주면 된다. 이 때, 누적합하나 자체가 목적값(N)인 경우 + 1을 해준다.
//		int ans = 0;
//		for(int i=1; i<=len; i++)
//		{
//			if(psum[i] == N)
//				++ans;
//				
//			ans += map.getOrDefault(psum[i] - N, 0);
//			
//			map.put(psum[i],map.getOrDefault(psum[i], 0) + 1);
//		}
//		System.out.print(ans);
//	}
//}