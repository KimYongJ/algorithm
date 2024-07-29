// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a,b)->b-a);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		int arr[] = new int[n];
		
		if(n <= k) {
			System.out.print(0);
			return;
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
	
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