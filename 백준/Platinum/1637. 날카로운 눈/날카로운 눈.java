//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1637
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	public static long getSum(int N, int[] A, int[] B, int[] C, long target) {
		long sum = 0;
		for(int i=0; i<N; i++)
			if(A[i] <= target)
				sum += ((Math.min(target, B[i]) - A[i]) / C[i]) + 1;
		return sum;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N	= Integer.parseInt(br.readLine());	// 1<=이만
		int A[] = new int[N];
		int B[] = new int[N];
		int C[] = new int[N];
		long s	= 1;
		long e	= 0;
		for(int i=0; i<N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
			C[i] = Integer.parseInt(st.nextToken());
			e = Math.max(B[i], e);
		}
		long res = -1;
		while(s <= e)
		{
			long mid = (s + e) >> 1;
			
			long sum = getSum(N, A, B, C, mid);
			if(sum % 2 == 0)
				s = mid + 1;
			else
			{
				e	= mid - 1;
				res = mid;
			}
		}
		if(res == -1)
		{
			System.out.print("NOTHING");
			return;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(res).append(' ');
		sb.append(getSum(N, A, B, C, res) - getSum(N, A, B, C, res - 1));
		System.out.print(sb.toString());
	}
}