// https://github.com/kimyongj/algorithm
import java.util.Arrays;
import java.util.PriorityQueue;
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
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a,b)->b-a);
		int n = read();
		int k = read();
		int arr[] = new int[n];
		
		if(n <= k) {
			System.out.print(0);
			return;
		}
		
		for(int i=0; i<n; i++)
			arr[i] = read();
	
		Arrays.sort(arr);
		
		int sum = 0;
		for(int i=1; i<n; i++) 
		{
			int num = arr[i] - arr[i-1];
			sum += num;
			pq.add(num);
		}
		
		for(int i=1; i<k; i++)
			sum -=pq.poll();
		
		System.out.print(sum);
	}
}