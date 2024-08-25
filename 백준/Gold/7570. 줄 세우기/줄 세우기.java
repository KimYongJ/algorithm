//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/7570
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N+1];
		if(N <= 1) {
			System.out.print(0);
			return;
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[Integer.parseInt(st.nextToken())] = i;
		
		int res = 0;
		int cnt = 0;
		for(int i=2; i<=N; i++) {
			if(arr[i-1] < arr[i]) {
				cnt++;
			}else {
				res = Math.max(res, cnt + 1);
				cnt = 0;
			}
		}
		res = Math.max(res, cnt);
		System.out.print(N-res);
	}
}