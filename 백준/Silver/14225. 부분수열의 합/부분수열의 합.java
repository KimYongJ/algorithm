//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/14225
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{

	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		int arr[]	= new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(arr);
		
		int res = 1;
		
		for(int i=0; i<N; i++)
		{
			if(res < arr[i])
				break;
			res += arr[i];
		}
		
		System.out.print(res);
	}
}