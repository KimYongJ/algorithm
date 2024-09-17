//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2470
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine()); 	// 전체 용액수 N(2<=십만)
		int arr[]	= new int[N];						// 용액의 특성값(-십억<=십억)
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(arr);
		
		int res1	= 0;
		int res2	= 0;
		int s		= 0;
		int e		= N-1;
		int diff	= Integer.MAX_VALUE;
		while(s < e)
		{
			int sum = arr[s] + arr[e];
			if(Math.abs(sum) < diff)
			{
				diff = Math.abs(sum);
				res1 = arr[s];
				res2 = arr[e];
			}
			if(sum < 0) s++;
            else e--;
		}


		System.out.print( new StringBuilder().append(res1).append(' ').append(res2).toString() );
	}
}