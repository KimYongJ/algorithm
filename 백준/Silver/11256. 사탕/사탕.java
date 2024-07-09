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
		int J, N, cnt, arr[];
		int T = read();
		while(T-->0)
		{
			J	= read(); // 사탕 개수
			N	= read(); // 상자의 개수
			arr = new int[N];
			cnt = 0;
			
			for(int i=0; i<N; i++) 
			{
				arr[i] = read() * read();
			}
			
			Arrays.sort(arr);
			
			while(N-->0 && J > 0) 
			{
				cnt++;
				J -= arr[N];
			}
			
			sb.append(cnt).append('\n');
		}
		System.out.print(sb);
	}
}