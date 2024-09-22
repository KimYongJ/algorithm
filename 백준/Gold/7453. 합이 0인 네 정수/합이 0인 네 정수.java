//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/7453
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());	// 1 ≤ n ≤ 4000
		int LEN		= N * N;
		int A[]		= new int[N];
		int B[]		= new int[N];
		int C[]		= new int[N];
		int D[]		= new int[N];
		int AB[]	= new int[LEN];
		int CD[]	= new int[LEN];
		
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
				while(left + 1 < LEN && AB[left + 1] == AB[left])
				{
					left++;
					leftCnt++;
				}
				while(0 <= right - 1 && CD[right - 1] == CD[right])
				{
					right--;
					rightCnt++;
				}
				cnt += leftCnt * rightCnt;
				left++;
			}
		}
		
		System.out.print(cnt);
	}
}