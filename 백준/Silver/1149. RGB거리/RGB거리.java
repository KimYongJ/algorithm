// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][]arr = new int[N+1][3]; 
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[i][0] = Math.min(arr[i-1][1],arr[i-1][2])+r;
			arr[i][1] = Math.min(arr[i-1][0],arr[i-1][2])+g;
			arr[i][2] = Math.min(arr[i-1][1],arr[i-1][0])+b;
		}
		System.out.println(Math.min(Math.min(arr[N][0], arr[N][1]), arr[N][2]));
	}
}