// https://github.com/kimyongj/algorithm
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
			int N		= read();
			int arr[]	= new int[N];
			int base[]	= new int[N];
			
			for(int i=0; i<N; i++)
				base[i] = read();
			
			Arrays.sort(base);
			
			int left	= 0;
			int right	= N-1;
			boolean flag = true;
			
			for(int i=0; i<N; i++) 
				if(flag = !flag)arr[left++] = base[i];
				else arr[right--] = base[i];
			
			int MAX = 0;
			for(int i=1; i<N; i++)
			{
				int diff = Math.abs(arr[i] - arr[i-1]);
				if(MAX < diff)
					MAX = diff;
			}
			sb.append(MAX).append('\n');
		}
		System.out.print(sb.toString());
	}
}