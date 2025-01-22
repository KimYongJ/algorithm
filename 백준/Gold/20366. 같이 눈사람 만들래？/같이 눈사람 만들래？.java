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
		for(int i=0; i<N-1; i++)
		{
			for(int j=i+1; j<N; j++)
			{
				int s = plus1(0,i,j);
				int e = plus2(1, i, j, s);
				while(s<N && e<N)
				{
					int sumsi = arr[s] + arr[i];
					int sumej = arr[e] + arr[j];
					int abs1 = Math.abs(sumsi - sumej);
					if(sumsi < sumej)
						s = plus2(s+1,i,j, e);
					else e = plus2(e+1, i,j,s);
					min = Math.min(min, abs1);
				}
				s = plus1(0,i,j);
				e = plus2(1, i, j, s);
				while(s<N && e<N)
				{
					int sumsj = arr[s] + arr[j];
					int sumei = arr[e] + arr[i];
					int abs1 = Math.abs(sumsj - sumei);
					if(sumsj < sumei)
						s = plus2(s+1,i,j, e);
					else e = plus2(e+1, i,j,s);
					min = Math.min(min, abs1);
				}
			}
		}
		System.out.print(min);
	}
	public static int plus2(int s, int n1, int n2, int n3) {
		while(s == n1 || s == n2 || s == n3)++s;
		return s;
	}
	public static int plus1(int s, int n1, int n2) {
		while(s == n1 || s == n2)++s;
		return s;
	}
}
