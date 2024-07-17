// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br	= new BufferedReader(new InputStreamReader(System.in));
		int N				= Integer.parseInt(br.readLine());
		int arr[]			= new int[N];
		StringTokenizer st	= new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) 
			arr[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(arr);
		
		int idx = N / 2;
		if(N % 2 == 0) 
			idx--;
		System.out.print(arr[idx]);
	}
}