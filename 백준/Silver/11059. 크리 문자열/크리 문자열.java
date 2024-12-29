//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11059
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str	= br.readLine();
		int len		= str.length();
		int psum[]	= new int[len+1];
		
		for(int i=1; i<=len; i++)
			psum[i] = str.charAt(i-1) - '0' + psum[i-1];
		
		int start = len % 2 == 0 ? len : len - 1;
		for(int i=start; i>=2; i-=2)
		{
			for(int s=0,e=i; e<=len; s++,e++)
			{
				int mid = (s + e) >> 1;
				int left = psum[mid] - psum[s];
				int right = psum[e] - psum[mid];
				if(left == right) {
					System.out.print(i);
					return;
				}
			}
		}
		
	}
}