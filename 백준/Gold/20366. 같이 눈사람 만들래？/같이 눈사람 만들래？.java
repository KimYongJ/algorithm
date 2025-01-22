//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/20366
//2초 / 1024MB
//요약 : 배열에서 2개씩 2번골라서 각각 합친 값의 차이가 최소인 값 출력 
import java.util.Arrays;
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();		// 4<=600
		int arr[]	= new int[N];
		for(int i=0; i<N; i++)
			arr[i] = read();		// 지름(1<=십억)
		
		Arrays.sort(arr);
		
		int min = Integer.MAX_VALUE;
		
		for(int l=0; l<N; l++)
			for(int r=l+3; r<N; r++)
			{
				int sum1 = arr[l] + arr[r];
				int s = l+1;
				int e = r-1;
				while(s<e)
				{
					int sum2 = arr[s] + arr[e];
					
					min = Math.min(min, Math.abs(sum1-sum2));
					
					if(sum1 < sum2)
						--e;
					else
						++s;
					
					if(min == 0)
					{
						System.out.print(0);
						return;
					}
				}
			}
		
		System.out.print(min);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}