//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/25947
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	
	static int N, M, G, arr[];
	static long[] psum, half;
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());	// 선물개수(1<=십만)
		M		= Integer.parseInt(st.nextToken());	// 예산(1<=십억)
		G		= Integer.parseInt(st.nextToken());	// 반값 가능한 최대선물수(0<=N)
		arr		= new int[N+1];
		psum	= new long[N+1];
		half	= new long[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(arr);
		
		for(int i=1; i<=N; i++)
		{
			psum[i] = arr[i] + psum[i-1];
			half[i] = psum[i]>>1;
		}
		
		int left	= 1;
		int right	= N;
		int res		= 0;
		
		while(left <= right)
		{
			int mid = (left + right) >> 1;
			long cost = validate(mid);
			if(cost <= M)
			{
				left = mid + 1;
				res = mid;
			}
			else right = mid - 1;
		}
		System.out.print(res);
	}
	public static long validate(int cnt) {
		if(cnt <= G)
			return half[cnt];

		return half[cnt] - half[cnt-G] + psum[cnt-G];
	}
}