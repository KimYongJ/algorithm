// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int cnt = 0;
		int len = str.length();
		boolean flag[] = new boolean[len+1];
		
		for(int i=0; i<len; i++) 
		{
			flag[i+1] = str.charAt(i) =='Y';
		}
		
		for(int i=1; i<=len; i++) {
			if(flag[i]) {
				cnt++;
				for(int idx = 1; idx*i<=len; idx++) 
				{
					flag[idx*i] = !flag[idx*i];
				}
			}
		}
		System.out.print(cnt);
	}
}