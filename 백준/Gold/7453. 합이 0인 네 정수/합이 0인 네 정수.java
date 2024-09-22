//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/7453
import java.util.Arrays;
class Main{
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) {
        n = (n << 3) + (n << 1) + (c & 15);}
        return m ? ~n + 1 : n;
    }
	public static void main(String[] args)throws Exception{
		int N		= read();	// 1 ≤ n ≤ 4000
		int LEN		= N * N;
		int A[]		= new int[N];
		int B[]		= new int[N];
		int C[]		= new int[N];
		int D[]		= new int[N];
		int AB[]	= new int[LEN];
		int CD[]	= new int[LEN];
		
		for(int i=0; i<N; i++)
		{
			A[i] = read();
			B[i] = read();
			C[i] = read();
			D[i] = read();
		}
		
		for(int i=0, len = 0; i<N; i++) 
			for(int j=0; j<N; j++, len++)
			{
				AB[len] = A[i] + B[j];
				CD[len] = C[i] + D[j];
			}
		
		Arrays.sort(AB);
		Arrays.sort(CD);
		
		long cnt	= 0;
		int left	= 0;
		int right	= LEN-1;
		
		while(0<=right && left < LEN)
		{
			int sum = AB[left] + CD[right];
			if(0 < sum)
				right--;
			else if(sum < 0)
				left++;
			else
			{
				long leftCnt	= 1;
				long rightCnt	= 1;
				while(++left< LEN && AB[left] == AB[left - 1])
					leftCnt++;
				while(0 <= --right && CD[right] == CD[right + 1])
					rightCnt++;
				
				cnt += leftCnt * rightCnt;
			}
		}
		
		System.out.print(cnt);
	}
}