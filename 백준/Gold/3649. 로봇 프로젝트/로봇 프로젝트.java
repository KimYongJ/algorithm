//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/3649
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			String str = br.readLine();
			if(str == null || str.length() == 0) {
				break;
			}
			
			int X = Integer.parseInt(str) * 10_000_000;	// 구멍의 넓이 cm(1<=20) => 나노미터로전환(1센티미터 = 10,000,000 나노미터)
			int N = Integer.parseInt(br.readLine());	// 레고조각수 (0<=백만)
			int arr[] = new int[N];						// 레고조각의 길이 L(나노미터 단위 1<=100,000,000)
			
			for(int i=0; i<N; i++)
				arr[i] = Integer.parseInt(br.readLine());
			
			Arrays.sort(arr);
			
			int s = 0;
			int e = N-1;
			int res1 = 0;
			int res2 = 0;
			int diff = -1;
			while(s < e)
			{
				int sum = arr[s] + arr[e];
				if(sum == X)
				{
					if(diff < 0 || arr[e] - arr[s] > diff)
					{
						res1 = arr[s];
						res2 = arr[e];
						diff = arr[e] - arr[s];
					}
				}
				if(sum <= X) s++;
				else e--;
			}
			
			if(diff < 0) {
				sb.append("danger\n");
			}else {
				sb.append("yes ").append(res1).append(' ').append(res2).append('\n');
			}
		}
		System.out.print(sb.toString());
	}
}