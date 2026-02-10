//https://www.acmicpc.net/problem/31870
//1초 1024MB
//5
//2 5 4 1 3
//답 : 5
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int arr[] = new int[N];
		int brr[] = new int[N];
		
		for(int i=0; i<N; i++)
			arr[i] = brr[i] = Integer.parseInt(st.nextToken());
		
		int cnt1 = 0;
		int cnt2 = 0;
		for(int i=0; i<N; i++)
		{
			int len = N - i;
			for(int j=1; j<len; j++)
			{
				if(arr[j - 1] > arr[j])
				{
					int tmp = arr[j-1];
					arr[j-1] = arr[j];
					arr[j] = tmp;
					++cnt1;
				}
				if(brr[j - 1] < brr[j])
				{
					int tmp = brr[j - 1];
					brr[j - 1] = brr[j];
					brr[j] = tmp;
					++cnt2;
				}
			}
		}
		System.out.print(Math.min(cnt1, cnt2 + 1));
	}
}