// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int arr[] = new int[N];
		int index[] = new int[K-1];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a,b)->b[0]-a[0]);
		for(int i=1; i<N; i++)
			pq.add(new int[] {arr[i]-arr[i-1],i-1});
		
		for(int i=0; i<K-1; i++)
			index[i] = pq.poll()[1];
		
		Arrays.sort(index);
		
		long sum = 0;
		int start = 0;
		for(int len : index) {
			sum += arr[len] - arr[start];
			start = len+1;
		}
		if(start < N) {
			sum += arr[N-1] - arr[start];
		}
		System.out.print(sum);
	}
}
