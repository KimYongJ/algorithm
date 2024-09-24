//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/9007
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static int getNum(int arr[], int target, int e, int s)
	{
		int res = 0;
		int abs = 40_000_001;
		while(s <= e)
		{
			int mid		= (s + e) >> 1;
			int sum		= target - arr[mid];
			int sumAbs	= Math.abs(sum);
			
			if(sum == 0)
				return  arr[mid];
			
			if(sumAbs < abs)
			{
				res = arr[mid];
				abs = sumAbs;
			}
			else if(sumAbs == abs && arr[mid] < res) 
				res = arr[mid];
			
			if(sum < 0)
				e = mid - 1;
			else
				s = mid + 1;
		}
		return res;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int K		= Integer.parseInt(st.nextToken());	// 보트값(1<=사천만)
			int N		= Integer.parseInt(st.nextToken());	// 각반학생수(1<=천)
			int	len		= N * N;
			int A[]		= new int[N];
			int B[]		= new int[N];
			int C[]		= new int[N];
			int D[]		= new int[N];
			int AB[]	= new int[len];
			int CD[]	= new int[len];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++)
				A[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++)
				B[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++)
				C[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++)
				D[i] = Integer.parseInt(st.nextToken());
			
			Arrays.sort(C);
			Arrays.sort(D);
			
			for(int i=0,idx = 0; i<N; i++)	// 데이터 한줄로 가공
				for(int j=0; j<N; j++, idx++)
				{
					AB[idx] = A[i] + B[j];
					CD[idx] = C[i] + D[j];
				}

			Arrays.sort(CD);
			
			int res = 0;
			int abs = 40_000_001;
			for(int i=0; i<len; i++)
			{
				int sum = AB[i] + getNum(CD, K - AB[i], len - 1, 0);
				int K_sum_abs = Math.abs(K - sum);
				
				if(K_sum_abs < abs)
				{
					res = sum;
					abs = K_sum_abs;
				}
				else if(K_sum_abs == abs && sum < res)
					res = sum;
			}
			
			sb.append(res).append('\n');
		}
		System.out.print(sb.toString());
	}
}