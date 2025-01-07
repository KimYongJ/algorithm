//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/3273
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int arr[] = new int[N];
		
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(arr);
		
		int x = Integer.parseInt(br.readLine());
		int s = 0;
		int e = N-1;
		int c = 0;
		while(s<e) {
			int sum = arr[e] + arr[s];
			if(sum == x)
			{
				++c;
				++s;
			}
			else if(sum < x)++s;
			else --e;
		}
		
		System.out.print(c);
	}
}