// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s	= br.readLine();
		String p	= br.readLine();
		int len		= p.length();
		int sIdx	= 0;
		int cnt		= 0;
		boolean isContinue;
		while(true) 
		{
			cnt++;
			isContinue = false;
			for(int i=sIdx+1; i<=len; i++) {
				if(!s.contains(p.substring(sIdx,i))){
					sIdx = i-1;
					isContinue = true;
					break;
				}
			}
			if(!isContinue) {
				System.out.print(cnt);
				return;
			}
		}
	}
}