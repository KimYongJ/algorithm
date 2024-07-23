// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		int arr[]	= new int[N];
		int idx		= 0;
		int cnt		= 1;
		for(int i=0; i<N; i++) 
		{
			arr[i] = Integer.parseInt(br.readLine());
			if(arr[i] == N) 
			{
				idx = i;
			}
		}
		if(arr[0] == N) 
		{
			System.out.print(N-1);
			return;
		}
		int base = arr[idx];
		for(int i=idx-1; i>=0; i--) 
		{
			if(base-1 == arr[i]) 
			{
				--base;
				++cnt;
			}
		}
		System.out.print(N - cnt);
	}
}