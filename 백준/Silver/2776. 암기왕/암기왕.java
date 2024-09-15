//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/2776
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			int N = Integer.parseInt(br.readLine());
			int arr[] = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr);
			int M = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			while(M-->0) {
				int num = Integer.parseInt(st.nextToken());
				int s = 0;
				int e = N-1;
				boolean flag = false;
				while(s <= e) {
					int mid = (s + e) / 2;
					if(arr[mid] == num) {
						flag = true;
						break;
					}
					if(arr[mid] < num) {
						s = mid + 1;
					}else {
						e = mid - 1;
					}
				}
				sb.append(flag ? 1 : 0).append('\n');
			}
		}
		System.out.print(sb.toString());
	}
}