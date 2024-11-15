//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1059
import java.util.Arrays;
class Main{
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();		// 배열의 크기 L(1<=50)
		int arr[]	= new int[N+1];	// 배열S(정수(1<=1000), 중복수 없음)
		
		for(int i=1; i<=N; i++)
			arr[i] = read();
		
		int T = read();
		
		Arrays.sort(arr);

		for(int i=1; i<=N; i++)
		{
			int s = arr[i-1] + 1;
			int e = arr[i] - 1;
			if(s<=T && T<=e)
			{
				System.out.print(((T-s+1) * (e-T+1)) - 1);
				return;
			}
		}

		System.out.print(0);
	}
}
