//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2118
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		int len		= N<<1;
		int arr[]	= new int[len+1];
		int distSum = 0;
		int curdist = 0;
		int max		= 0;
		int s		= 1;
		int e		= 1;
		
		for(int i=1; i<=N; i++)
			distSum += arr[i] = arr[i+N] = Integer.parseInt(br.readLine());

		while(s<=N)
		{
			while(true)
			{
				curdist += arr[e];
				if(curdist > distSum / 2)
				{
					curdist -= arr[e];
					break;
				}
				e++;
			}
			
			if(max < curdist)
				max = curdist;
			
			curdist -= arr[s];
			++s;
		}
		System.out.print(max);
	}
}