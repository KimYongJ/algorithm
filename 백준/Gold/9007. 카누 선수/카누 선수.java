//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/9007
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static int getSum(int arr1[], int arr2[], int K, int len)
	{
		int s	= 0;
		int e	= len - 1;
		int sum = 0;
		int abs = Integer.MAX_VALUE;
		while(s < len && 0 <= e)
		{
			int total = arr1[s] + arr2[e];
			int totalABS = Math.abs(K - total);
			
			if(totalABS < abs)
			{
				abs = totalABS;
				sum = total;
			}
			else if(totalABS == abs && total < sum)
				sum = total;
			
			if(total == K)
				break;
			else if(total < K)
				s++;
			else
				e--;
		}
		return sum;
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
			
			Arrays.sort(A);
			Arrays.sort(B);
			Arrays.sort(C);
			Arrays.sort(D);
			
			for(int i=0,idx = 0; i<N; i++)	// 데이터 한줄로 가공
				for(int j=0; j<N; j++, idx++)
				{
					AB[idx] = A[i] + B[j];
					CD[idx] = C[i] + D[j];
				}

			Arrays.sort(AB);
			Arrays.sort(CD);
			
			sb.append( getSum(AB, CD, K, len) ).append('\n');
		}
		System.out.print(sb.toString());
	}
}