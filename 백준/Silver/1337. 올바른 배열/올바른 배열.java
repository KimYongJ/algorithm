//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1337
//2초 / 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());//(1<=50)
		int arr[]	= new int[N];
		int min		= 1<<30;
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(br.readLine());//(0<=10억)
		
		Arrays.sort(arr);
		
		for(int i=0; i<N; i++)
			min = Math.min(min, cal(arr, i, N));

		System.out.print(min);
	}
	public static int cal(int arr[], int idx, int N) {
		int cnt = 0;
		int start = arr[idx];
		int end = start + 5;
		while(++start < end)
		{
			if(idx + 1 < N)
			{
				if(start != arr[idx+1])
					++cnt;
				else ++ idx;
			}
			else
				++cnt;
		}
		return cnt;
	}
}