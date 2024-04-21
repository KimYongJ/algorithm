// https://github.com/kimyongj/algorithm
import java.util.Arrays;
class Main{
	public static int read() throws Exception {
		int c, n = System.in.read() & 15;
		boolean isNegative = n == 13;
		if (isNegative) n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13) System.in.read();
		return isNegative ? ~n + 1 : n;
	}
	public static void main(String[] args)throws Exception{
		int left, right, num, cnt=0;
		int N	  = read();
		int arr[] = new int[N];
		
		for(int i=0; i<N; i++)
			arr[i] = read();
		
		Arrays.sort(arr);
		N--;
		
		for(int i=0; i<=N; i++)
		{
			left  = 0;
			right = N;
			while(left < right) 
			{
				if(i==left || i==right) {
					if(i == left)  left++;
					if(i == right) right--;
					continue;
				}
				num = arr[left] + arr[right];
				if(num == arr[i]) {
					cnt++;
					break;
				}
				else if(num > arr[i])
					right--;
				else 
					left++;
			}
		}
		System.out.print(cnt);
	}
}