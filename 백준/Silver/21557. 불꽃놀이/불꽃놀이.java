// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N		= Integer.parseInt(br.readLine());
		int arr[]	= new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) 
		{
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int sub = Math.abs(arr[0] - arr[N-1]);
		int max = Math.max(arr[0], arr[N-1]);
		int N_3 = N - 3;
		
		if(sub > N_3) {
			System.out.print(max - (N - 2));
		}else {
			System.out.print(max - sub - 1 - (((N_3)-sub) / 2));
		}
		
	}
}