// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		while(T-->0) {
			int cnt = 0;
			int idx = 1;
			int N = Integer.parseInt(br.readLine())+1;
			int arr[] = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			for(int i=1; i<N; i++) 
			{
				for(int j=i; j<N; j++)
				{
					if(i == arr[j])
					{
						idx = j;
						break;
					}
				}
				while(arr[i] != i) {
					int tmp = arr[idx - 1];
					arr[idx - 1] = arr[idx];
					arr[idx] = tmp;
					idx--;
					cnt++;
				}
			}
			sb.append(cnt).append('\n');
		}
		System.out.print(sb.toString());
	}
}