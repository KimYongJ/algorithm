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
		int sum = 0;
		int n = read();
		int k = read();
		int arr[] = new int[n];
		int dif[] = new int[n-1];

		if(n <= k) {
			System.out.print(0);
			return;
		}
		
		for(int i=0; i<n; i++)
			arr[i] = read();

		Arrays.sort(arr);
		
		for(int i=1; i<n; i++) 
			sum += dif[i-1] = arr[i] - arr[i-1];
		
		Arrays.sort(dif);
		
		for(int i=1,j=n-2; i<k; i++,j--)
			sum -= dif[j];
		
		System.out.print(sum);
	}
}