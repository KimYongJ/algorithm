// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static int getIndex(char c) {
		switch(c) 
		{
			case 'B':return 0;
			case 'S':return 1;
			case 'G':return 2;
			case 'P':return 3;
			default:return 4;
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		int money[] = new int[5];
		int before	= 0;
		int result	= 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++)
		{
			money[i] = Integer.parseInt(st.nextToken()) - 1;
		}
		money[4] = money[3]+1;
		
		String str = br.readLine();
		for(int i=0; i<N; i++) 
		{
			int idx = getIndex(str.charAt(i));
			if(idx == 4) 
			{
				result += money[idx];
			}
			else 
			{
				result += before = money[idx] - before;;
			}
		}
		System.out.print(result);
	}
}