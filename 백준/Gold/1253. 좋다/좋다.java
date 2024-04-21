// https://github.com/kimyongj/algorithm
import java.util.Arrays;
class Main{
	
	static int N, arr[], left, right, cnt;
	public static void getLeft (int idx) {while(idx == left)  {left++;}}
	public static void getRight(int idx) {while(idx == right) {right--;}}
	public static int read() throws Exception {
		int c, n = System.in.read() & 15;
		boolean isNegative = n == 13;
		if (isNegative) n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13) System.in.read();
		return isNegative ? ~n + 1 : n;
	}
	public static boolean search(int baseNum, int baseIdx) {
		int num;
		while(left < right) {
			getLeft(baseIdx);
			getRight(baseIdx);
			if(right < 0 || left >= N)
				break;
			if(left == right)
				continue;
			
			num = arr[left] + arr[right];
			if(num == baseNum) 
				return true;
			else if(num > baseNum) 
				right--;
			else 
				left++;
		}
		return false;
	}
	public static void main(String[] args)throws Exception{
		N	= read();
		arr = new int[N];
		for(int i=0; i<N; i++)
			arr[i] = read();
		
		Arrays.sort(arr);
		
		for(int i=0; i<N; i++)  // 가장 큰 수부터 차례대로 탐색
		{
			left  = 0;
			right = N-1;
			if ( search(arr[i],i) )
				cnt++;
		}
		System.out.print(cnt);
	}
}