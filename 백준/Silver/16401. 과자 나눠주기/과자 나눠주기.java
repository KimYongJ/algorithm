// https://github.com/kimyongj/algorithm
import java.util.Arrays;

class Main{
	
	static int N, M, snack[], left, right, mid;
	static int read() throws Exception {// 빠른 입력을 위한 함수
	        int c, n = System.in.read() & 15;
	        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
	        return n;
	}
	public static boolean check(int mid) {
		int people = 0;
		for(int i=N-1; i>=0; i--) 
		{
			people += snack[i]/mid;
			if(people >= M)
				return true;
		}
		return false;
	}
	public static void main(String[] args)throws Exception{
		M 		= read(); // 조카의 수 
		N 		= read(); // 과자의 수 
		snack 	= new int[N];
		
		Arrays.sort(snack);

		for(int i=0; i<N; i++) 
			snack[i] = read();
		
		left = 1;
		right = 1_000_000_000;
        
		while(left <= right)
        {
			mid = (left + right) / 2;
			if(check(mid))
				left = mid+1;
			else
				right = mid-1;
		}
		System.out.println(left-1);
	}
}
