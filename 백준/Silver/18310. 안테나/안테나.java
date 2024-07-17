// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br	= new BufferedReader(new InputStreamReader(System.in));
		int N				= Integer.parseInt(br.readLine());
		int idx				= N / 2 + (N % 2 != 0 ? 1 : 0) ;
		int arr[]			= new int[100001];
		StringTokenizer st	= new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) 
			arr[Integer.parseInt(st.nextToken())]++;
				
		for(int i=0; i<=100000; i++) 
		{
			if(arr[i] != 0) 
			{
				idx -= arr[i];
				if(idx <= 0) 
				{
					System.out.print(i);
					return;
				}
			}
		}
	}
}