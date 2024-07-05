// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int result = 0;
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		for(int i=0; i<N; i++) {
			int cnt = 0;
			for(int j=i+1; j<N; j++) {
				if(arr[i] > arr[j]) {
					cnt++;
				}else {
					i = j-1;
					break;
				}
			}
			if(result < cnt) {
				result = cnt;
			}
		}
		System.out.print(result);
	}
}