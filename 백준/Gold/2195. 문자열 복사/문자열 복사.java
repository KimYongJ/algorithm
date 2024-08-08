// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String p = br.readLine();
		int cnt = 0;
		Loop : 
		while(p.length() > 1) {
			cnt++;
			for(int i=1; i<=p.length(); i++) {
				if(!s.contains(p.substring(0,i))){
					p = p.substring(i-1);
					continue Loop;
				}
			}
			System.out.print(cnt);
			return;
		}
		if(p.length() != 0) cnt++;
		System.out.print(cnt);
	}
}
