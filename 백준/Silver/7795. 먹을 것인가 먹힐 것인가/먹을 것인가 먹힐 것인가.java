//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/7795
import java.util.Arrays;
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int T = read();
		while(T-->0)
		{
			int N = read();
			int M = read();
			int res = 0;
            int idx = 0;
			int arr1[] = new int[N];
			int arr2[] = new int[M];
			for(int i=0; i<N; i++)
				arr1[i] = read();
			for(int i=0; i<M; i++)
				arr2[i] = read();
			
			Arrays.sort(arr1);
			Arrays.sort(arr2);
			
			for(int n : arr1)
			{
				while(idx < M && arr2[idx] < n)
					idx++;
				
				if(idx <= M)
					res += idx;
			}
			sb.append(res).append('\n');
		}
		System.out.print(sb.toString());
	}
}