// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) 
		{
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int sum	= 0;
		int cnt = 0;
		for(int i=0; i<N; i++) 
		{
			sum += arr[i];		// 지금까지 만든 체인개수
			cnt = N - 1 - i;	// 특정 인덱스일 때 남은 체인 개수
			if(sum >= cnt) {
				break;
			}
		}
		System.out.print(cnt);
	}
}