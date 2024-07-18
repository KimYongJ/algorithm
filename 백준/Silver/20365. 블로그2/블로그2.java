// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();
		String str = br.readLine();
		int len = str.length();
		char now = '1';
		int Rcnt = 0;
		int Bcnt = 0;
		for(int i=0; i<len; i++) 
		{
			char c = str.charAt(i);
			if(c != now) {
				now = c;
				if(now == 'B')Bcnt++;
				else Rcnt++;
			}
		}
		if(Rcnt == 0 || Bcnt == 0) {
			System.out.print(1);
		}else {
			System.out.print(Math.min(Bcnt, Rcnt) + 1);
		}
	}
}