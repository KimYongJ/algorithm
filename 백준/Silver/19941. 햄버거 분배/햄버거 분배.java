// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N			= Integer.parseInt(st.nextToken());
		int K			= Integer.parseInt(st.nextToken());
		char arr[]		= br.readLine().toCharArray();
		int left, right, cnt = 0;
		for(int i=0; i<N; i++) 
		{
			if(arr[i] == 'P') 
			{
				left	= Math.max(i - K, 0);
				right	= Math.min(i + K, N-1);
				for(;left<=right; left++) 
				{
					if(arr[left] == 'H') 
					{
						arr[left] = 'X';
						cnt++;
						break;
					}
				}
			}
		}
		System.out.print(cnt);
	}
}