//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/26091
//1초 / 1024MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());	//총인원(1<=100,000)
		int M		= Integer.parseInt(st.nextToken());	//팀의 최소 능력치(1<=10의9승)
		int cnt		= 0;
		int arr[]	= new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(arr);
		
		int s = 0;
		int e = N - 1;
		while(s<e)
		{
			int sum = arr[e] + arr[s];
			if(sum >= M)
			{
				e--;
				s++;
				cnt++;
			}
			else
				s++;
		}
		System.out.print(cnt);
	}
}