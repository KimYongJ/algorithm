//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/27651
//1초 / 1024MB
//요약 : 일차원 배열을 머리 가슴 배 순서대로 나눈 후 각 구간의 합이 가슴>배>머리 순이 될 수 있는 모든 x,y쌍 개수 출력
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static int N;
	static long psum[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N		= Integer.parseInt(br.readLine());//배열수 (3<=백만)
		psum	= new long[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			psum[i] += psum[i-1] + Integer.parseInt(st.nextToken());// 원소수(1<=십만)
		
		long res = 0;
		int s = 1;
		int e = getE(N, psum[s]);
		while(psum[N] - psum[e-1] <= psum[e-1] - psum[s])
		{
			// 배보다 큰 가장 작은 인덱스
			int idx = binarySearch(s, e-1);
			if(idx < 0)
				break;
			res += e - idx;
			
			e = getE(e, psum[++s]);//다음 s와 e를 구한다.
		}
		System.out.print(res);
	}
	public static int binarySearch(int s, int e) {
		// l , r 범위 안에서 last보다 middle이 큰 가장 왼쪽 인덱스
		int l = s+1;
		int r = e;
		int idx = -1;
		while(l <= r)
		{
			int mid		= (l + r) >> 1;
			long middle = psum[mid] - psum[s];
			long last	= psum[N] - psum[mid];
			// 가운데가 
			if(last < middle)
			{
				idx = mid;
				r = mid - 1;
			}
			else
				l = mid + 1;
		}
		return idx;
	}
	public static int getE(int idx, long target) {
		while(psum[N] - psum[idx-1] <= target)--idx;
		return idx;
	}
}