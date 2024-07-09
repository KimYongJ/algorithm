// https://github.com/kimyongj/algorithm
import java.util.Arrays;
class Main{
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void main(String[] args)throws Exception{
		int N		= read(); // 과일 개수 N
		int L		= read(); // 초기 길이
		int arr[]	= new int[N];
		
		for(int i=0; i<N; i++)
		{
			arr[i] = read();
		}
		
		Arrays.sort(arr);
		
		for(int i=0; i<N && arr[i] <= L; i++) 
		{
			L++;
		}
		
		System.out.print(L);
	}
}