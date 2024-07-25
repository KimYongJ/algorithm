// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		int Acnt	= 1;
		int Bcnt	= 0;
		String str1 = br.readLine();
		String str2 = br.readLine();
		boolean A[] = new boolean[N]; // 첫번째를 눌렀을 경우
		boolean B[] = new boolean[N]; // 안 누른 경우
		boolean base[] = new boolean[N];

		for(int i=0; i<N; i++)
		{
			base[i] = str1.charAt(i) == '1';
			A[i] = B[i] = str2.charAt(i) == '1';
		}
		A[0] = !A[0];
		A[1] = !A[1];
		
		for(int i=1; i<N; i++)
		{
			if(A[i-1] != base[i-1]) 
			{
				Acnt++;
				A[i] = !A[i];
				if(i>0)		A[i-1] = !A[i-1];
				if(i<N-1)	A[i+1] = !A[i+1];
			}
			if(B[i-1] != base[i-1])
			{
				Bcnt++;
				B[i] = !B[i];
				if(i>0)		B[i-1] = !B[i-1];
				if(i<N-1)	B[i+1] = !B[i+1];
			}
		}
		if(A[N-1] != base[N-1])Acnt = 100000;
		if(B[N-1] != base[N-1])Bcnt = 100000;
		
		int res = Math.min(Acnt, Bcnt);
		
		System.out.print(res == 100000 ? -1 : res);
	}
}