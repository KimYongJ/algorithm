// https://github.com/kimyongj/algorithm
// https://www.acmicpc.net/problem/2923
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int T	= read();
		int A[] = new int[101];
		int B[] = new int[101];
		int AA[];
		int BB[];
		while(T-->0)
		{
			A[read()]++;
			B[read()]++;
			AA = new int[101];
			BB = new int[101];
			for(int i=1; i<=100; i++) 
			{
				AA[i] = A[i];
				BB[i] = B[i];
			}
			int left	= 0;
			int right	= 100;
			int min,max	= 0;
			while(true) 
			{
				while(left<=100 && AA[left]==0)left++;
				while(right>=0 && BB[right]==0)right--;
				if(left > 100 || right < 0)
					break;
				
				max = Math.max(max, left + right);
				min = Math.min(AA[left], BB[right]);
				
				AA[left]	-= min;
				BB[right]	-= min;
			}
			sb.append(max).append('\n');
		}
		System.out.print(sb.toString());
	}
}
/*
5
99 1
99 1
5 6
6 6
6 6
출력
100
100
100
100
100
 * */
