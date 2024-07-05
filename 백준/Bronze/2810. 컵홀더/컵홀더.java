// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{

	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int len		= Integer.parseInt(br.readLine());
		String str	= br.readLine();
		int xCnt	= 1;
		int Lcnt	= 0;
		for(int i=0; i<len; i++) 
		{
			if(str.charAt(i) == 'S') {
				xCnt++;
			}else {
				Lcnt++;
			}
		}
		
		xCnt += (Lcnt / 2);

		System.out.print(xCnt >= len ? len : xCnt);
	}
}