//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/25916
//1초 / 1024MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());	// 배열개수(1≤오십만)
		int M		= Integer.parseInt(st.nextToken());	// 사용가능한 최대 값(1≤10의9승)
		int arr[]	= new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		int s	= 0;
		int e	= 0;
		int sum = 0;
		int res = 0;
		
		while(e<N)
		{
			sum += arr[e++];
			
			while(M < sum)
				sum -= arr[s++];
			
			res = Math.max(res, sum);
		}
		
		System.out.print(res);
	}
}