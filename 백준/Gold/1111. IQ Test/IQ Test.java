//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1111
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static int N, arr[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N	= Integer.parseInt(br.readLine());	// 1<=50
		arr = new int[N];						// 절대값 100이하 다음수가 여러개면 A출력, 없으면 B출력
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		if(N == 1)
		{
			System.out.print('A');
			return;
		}
		int start = arr[0];
		boolean equal = true;
		
		for(int i=0; i<N; i++)
			if(arr[i] != start)
			{
				equal = false;
				break;
			}

		if(equal)
		{
			System.out.print(start);
			return;
		}
		if(N == 2)
		{
			System.out.print('A');
			return;
		}
		if(arr[0] == arr[1]) {
			System.out.print('B');
			return;
		}
		int A = (arr[2] - arr[1]) / (arr[1] - arr[0]);
		int B = arr[2] - arr[1] * A;
		for(int i=1; i<N; i++)
			if(arr[i-1]* A + B != arr[i])
			{
				System.out.print('B');
				return;
			}
		System.out.print(arr[arr.length-1] * A + B);
	}
}