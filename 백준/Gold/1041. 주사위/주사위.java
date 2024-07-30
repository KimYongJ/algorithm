// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	static long arr[];
	static int two[][] = {{0,1},{0,2},{0,3},{0,4},{1,2},{1,3},{1,5},{2,4},{2,5},{3,4},{3,5},{4,5}}; 
	static int three[][] = {{0,1,2},{0,1,3},{0,2,4},{0,3,4},{1,3,5},{1,2,5},{2,4,5},{3,4,5}};
	static long getMin(int[][] v) {
		long min = 999999999;
		for(int t[] : v) 
		{
			long n = 0;
			for(int i=0; i<t.length; i++) 
				n += arr[t[i]];
			if(min > n)
				min = n;
		}
		return min;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new long[6];
		long N = Long.parseLong(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		long min1 = 9999999;
		long max = 0;
		long sum = 0;
		for(int i=0; i<6; i++) 
		{
			sum += arr[i] = Long.parseLong(st.nextToken());
			if(arr[i] < min1) min1 = arr[i];
			if(arr[i] > max) max = arr[i];
		}
		if(N == 1) 
		{
			System.out.print(sum - max);
			return;
		}
		long min2 = getMin(two);
		long min3 = getMin(three);
		
		long _3 = min3 * 4;
		long _2 = min2 * ((N*8)-12);
		long _1 = min1 * ((N*N*5) - 12 - (((N*8)-12)*2));
		System.out.print(_3 + _2 + _1);
	}
}