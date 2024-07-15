// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N		= Integer.parseInt(br.readLine());
		int arr[]	= new int[50001];
		int max		= 0;
		int num;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
		{
			num = Integer.parseInt(st.nextToken());
			if(++arr[num] > max) 
			{
				max = arr[num];
			}
		}
		System.out.print(max);
	}
}