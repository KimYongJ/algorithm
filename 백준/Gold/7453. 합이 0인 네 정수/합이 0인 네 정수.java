//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/7453
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static int lowerBound(int[] list, int target, int len) {
		int idx	= -1;
		int s	= 0;
		int e	= len - 1;
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			if(list[mid] == target)
			{
				idx = mid;
				e = mid - 1;
			}
			else if(list[mid] < target)
				s = mid + 1;
			else
				e = mid - 1;
		}
		
		return idx;
	}
	public static int upperBound(int[] list, int target, int len) {
		int idx	= -1;
		int s	= 0;
		int e	= len - 1;
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			if(list[mid] == target)
			{
				idx = mid;
				s = mid + 1;
			}
			else if(list[mid] < target)
				s = mid + 1;
			else
				e = mid - 1;
		}
		return idx;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());	// 1 ≤ n ≤ 4000
		int LEN		= N * N;
		int A[]		= new int[N];
		int B[]		= new int[N];
		int C[]		= new int[N];
		int D[]		= new int[N];
		int list[]	= new int[LEN];
		
		for(int i=0; i<N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
			C[i] = Integer.parseInt(st.nextToken());
			D[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0, len = 0; i<N; i++) 
			for(int j=0; j<N; j++, len++)
				list[len] = A[i] + B[j];
		
		Arrays.sort(list);
		
		long cnt = 0;
		for(int i=0; i<N; i++)
		{
			for(int j=0; j<N; j++)
			{
				int target = -(C[i] + D[j]);
				int idx1 = lowerBound(list, target, LEN);
				if(idx1 != -1)
				{
					int idx2 = upperBound(list, target, LEN);
					cnt += idx2 - idx1 + 1;
				}
			}
		}
		System.out.print(cnt);
	}
}