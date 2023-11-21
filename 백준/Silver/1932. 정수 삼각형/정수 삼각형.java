// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[][]arr = new int[N][N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<=i; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=N-2; i>=0; i--)
			for(int j=0; j<=i; j++) 
				arr[i][j] += Math.max(arr[i+1][j],arr[i+1][j+1]);
	
		System.out.println(arr[0][0]);
	}
}