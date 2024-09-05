//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/18185
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int min, res= 0;
		int N		= Integer.parseInt(br.readLine()); // 라면공장개수 N (3<=만개)
		int arr[]	= new int[N+2];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++)
			if(arr[i] != 0)
			{
				res += 3 * arr[i];
				min = Math.min(arr[i], arr[i+1]);
				
				res += 2 * min;
				arr[i+1] -= min;
				
				min = Math.min(min, arr[i+2] - Math.min(arr[i+1], arr[i+2]));
				res += 2 * min;
				arr[i+2] -= min;
			}
		System.out.print(res);
	}
}
/*
10
7 5 3 2 1 7 5 3 2 1
답 89
 * */