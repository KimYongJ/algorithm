//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/22953
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static long MAX = 1_000_000_000_001L;
	static long min = MAX;
	static int N, F, C;
	static int time[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N	= Integer.parseInt(st.nextToken());	// 요리사수(1<=10)
		F	= Integer.parseInt(st.nextToken());	// 만들 음식개수(1<=백만)
		C	= Integer.parseInt(st.nextToken());	// 격려 횟 수(0<=5)
		time= new int[N];						//각 요리사의 조리 시간(1<=백만)
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			time[i] = Integer.parseInt(st.nextToken());
		
		back(0, 0);
		
		System.out.print(min);
	}
	public static void back(int depth, int idx) {
		binarySearch();
		
		if(depth == C)
			return;

		for(int i=idx; i<N; i++)
			if(1 < time[i])
			{
				time[i] -= 1;
				back(depth + 1, i);
				time[i] += 1;
			}
	}
	public static void binarySearch() {
		long s		= 0;
		long e		= MAX;
		long mid	= 0;
		while(s <= e)
		{
			mid = (s + e) >> 1;
			if(validate(mid))
			{
				e = mid - 1;
				if(mid < min)
					min = mid;
			}
			else s = mid + 1;
		}
	}
	public static boolean validate(long maxTime) {
		long cnt = 0;
		for(int i=0; i<N; i++)
		{
			cnt += maxTime / time[i];
			if(F <= cnt)
				return true;
		}
		return false;
	}
}