// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken()); // 박스 개수
		int M		= Integer.parseInt(st.nextToken()); // 박스의 무게 최대치
		int idx		= 0;
		int m		= 0;
		int arr[]	= new int[N];
		
		if(N > 0) 
		{
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) 
			{
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			idx = 1;
			for(int i=0; i<N; i++) 
			{
				if(m + arr[i] <= M) 
				{
					m += arr[i];
				}
				else 
				{
					idx ++;
					m = arr[i];
				}
			}
		}
		
		System.out.print(idx);
	}
}