//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/9007
import java.util.Arrays;
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static int getSum(int arr1[], int arr2[], int K, int len)
	{
		int s	= 0;
		int e	= len - 1;
		int sum = 0;
		int abs = Integer.MAX_VALUE;
		while(s < len && 0 <= e)
		{
			int total		= arr1[s] + arr2[e];
			int totalABS	= Math.abs(K - total);
			
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
		StringBuilder sb = new StringBuilder();
		int T = read();
		while(T-->0)
		{
			int K		= read();;	// 보트값(1<=사천만)
			int N		= read();;	// 각반학생수(1<=천)
			int	len		= N * N;
			int A[]		= new int[N];
			int B[]		= new int[N];
			int C[]		= new int[N];
			int D[]		= new int[N];
			int AB[]	= new int[len];
			int CD[]	= new int[len];
            
			for(int i=0; i<N; i++) A[i] = read();
			for(int i=0; i<N; i++) B[i] = read();
			for(int i=0; i<N; i++) C[i] = read();
			for(int i=0; i<N; i++) D[i] = read();
			
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