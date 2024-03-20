import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br 	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st 	= new StringTokenizer(br.readLine());
		StringBuilder sb 	= new StringBuilder();
		int N 				= Integer.parseInt(st.nextToken());
		int M 				= Integer.parseInt(st.nextToken());
		int arr[] 			= new int[N+1];
		int a,b,c;
		for(int i=1; i<=N; i++)
			arr[i] = i;
		for(int i=0; i<M; i++) {
			st 	= new StringTokenizer(br.readLine());
			a 	= Integer.parseInt(st.nextToken());
			b 	= Integer.parseInt(st.nextToken());
			c 	= arr[a];
			arr[a] = arr[b];
			arr[b] = c;
		}
		for(int i=1; i<=N; i++)
			sb.append(arr[i]).append(' ');
		System.out.println(sb);
	}
}