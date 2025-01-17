//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/6230
//1초 / 128MB
//요약 : 고품질 건초더미는 무조건 다 사고, 고품질 살 때마다 고품질 값 미만의 저품질을 하나 무료로 준다. 가장 많은 건초를 받게 매칭시키는 것 
import java.util.Arrays;
class Main{
	public static void main(String[] args)throws Exception{
		int N	= read();	// 고품질 건초(1<=만)
		int M	= read();	// 저품질 건초(1<=만);
		int a[] = new int[N];
		int b[] = new int[M];
		int res	= N;
		
		for(int i=0; i<N; i++)a[i] = read();
		for(int i=0; i<M; i++)b[i] = read();
		
		Arrays.sort(a);
		Arrays.sort(b);
		
		int bIdx = M-1;
		
		for(int i=N-1; i>=0; i--)
		{
			while(0<=bIdx && a[i] <= b[bIdx])
			{
				--bIdx;
			}
			
			if(bIdx<0)
				break;
			
			++res;
			--bIdx;
		}
		
		System.out.print(res);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}