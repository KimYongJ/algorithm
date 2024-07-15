// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 참가자들의 음의 개수 
		int A = Integer.parseInt(st.nextToken()); // 고음의 첫항
		int D = Integer.parseInt(st.nextToken()); // 공차
		int arr[] = new int[N];
		int X = 0;
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		for(int i=0; i<N; i++) 
		{
			if(arr[i] == A)
			{
				int x = 1;
				for(int j=i;j<N; j++) 
				{
					if(arr[i]+(x*D) == arr[j]) {
						x++;
					}
				}
				if(X < x) {
					X = x;
					break;
				}
			}
		}
		System.out.print(X);
	}
}