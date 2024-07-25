// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int arr[] = new int[N+1];
		if(K == 0) {
			for(int i=1; i<=N; i++) {
				sb.append(i).append(' ');
			}
			System.out.print(sb.toString());
			return;
		}
		for(int i=1; i<=N; i++) {
			arr[i] = i;
		}
		for(int i=1; i<=N; i++) {
			for(int j=N; j>i; j--) {
				int tmp = arr[j];
				arr[j] = arr[j-1];
				arr[j-1] = tmp;
				
				if(--K == 0) {
					for(int a=1;a<=N;a++) {
						sb.append(arr[a]).append(' ');
					}
					System.out.print(sb.toString());
					return;
				}
			}
		}
	}
}