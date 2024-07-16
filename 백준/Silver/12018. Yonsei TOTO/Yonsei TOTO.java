// https://github.com/kimyongj/algorithm
import java.util.Arrays;
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();		// 과목수
		int M		= read();		// 주어진 마일리지
		int arr[]	= new int[N];	// 내가 써야할 최소한의 마일리지
		
		int p, m, per[];
		for(int i=0; i<N; i++) 
		{
			p = read();				// 신청 사람 수
			m = read();				// 해당 과목의 수강인원
			per = new int[p];		// 각 사람이 수강신청한
			for(int j=0; j<p; j++) 
			{
				per[j] = read();
			}
			
			if(m > p) 
			{
				arr[i] = 1;
			}
			else 
			{
				Arrays.sort(per);
				arr[i] = per[p-m];
			}
		}
		Arrays.sort(arr);
		int cnt = 0;
		for(int i=0; i<N; i++)
		{
			if((M -= arr[i]) >= 0)cnt++;
			else break;
		}
		System.out.print(cnt);
	}
}