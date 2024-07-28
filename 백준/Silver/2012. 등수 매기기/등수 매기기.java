//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine())+1;
		int arr[] = new int[500001];
		
		for(int i=1; i<N; i++)
			arr[Integer.parseInt(br.readLine())]++;
		
		long res = 0;
		int rnk = 1;
		for(int i=1; i<500001; i++) 
		{
			while(arr[i]-- > 0) {
				res += Math.abs(i - rnk++);
			}
			if(rnk == N)
				break;
		}
		System.out.print(res);
	}
}