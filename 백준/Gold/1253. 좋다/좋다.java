// https://github.com/kimyongj/algorithm
import java.util.Arrays;
class Main{
	
	static int N, arr[], left, right, cnt, num;
	public static int read() throws Exception {
		int c, n = System.in.read() & 15;
		boolean isNegative = n == 13;
		if (isNegative) n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13) System.in.read();
		return isNegative ? ~n + 1 : n;
	}
	public static void main(String[] args)throws Exception{
		N	= read();
		arr = new int[N];
		for(int i=0; i<N; i++)
			arr[i] = read();
		
		Arrays.sort(arr);
		N--;
		for(int i=0; i<=N; i++)
		{
			left  = 0;
			right = N;
			if(i == left)  left++;
			if(i == right) right--;
			while(left < right) 
			{
				num = arr[left] + arr[right];
				if(num == arr[i]) {
					cnt++;
					break;
				}
				else if(num > arr[i]) {
					right--;
					if(i == right) right--;
				}
				else {
					left++;
					if(i == left)  left++;
				}
			}
		}
		System.out.print(cnt);
	}
}