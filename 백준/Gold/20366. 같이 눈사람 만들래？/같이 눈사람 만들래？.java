//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/20366
//2초 / 1024MB
//요약 : 배열에서 2개씩 2번골라서 각각 합친 값의 차이가 최소인 값 출력 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());// 4<=600
		StringTokenizer st = new StringTokenizer(br.readLine());
		int arr[] = new int[N];
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());// 지름(1<=십억)
		
		Arrays.sort(arr);
		
		int min = Integer.MAX_VALUE;
		for(int l=0; l<N; l++)
			for(int r=l+3; r<N && min != 0; r++)
			{
				int sum1 = arr[l] + arr[r];
				int s = l+1;
				int e = r-1;
				while(s<e)
				{
					int sum2 = arr[s] + arr[e];
					
					min = Math.min(min, Math.abs(sum1-sum2));
					
					if(sum1 < sum2)
						--e;
					else
						++s;
				}
			}
		
		System.out.print(min);
	}
}