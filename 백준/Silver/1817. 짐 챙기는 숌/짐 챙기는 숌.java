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
		int num;
		if(N > 0) 
		{
			st = new StringTokenizer(br.readLine());
			idx = 1;
			for(int i=0; i<N; i++) 
			{
				num = Integer.parseInt(st.nextToken());
				if(m + num <= M) 
				{
					m += num;
				}
				else 
				{
					idx ++;
					m = num;
				}
			}
		}
		
		System.out.print(idx);
	}
}