//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char str1[] = br.readLine().toCharArray();
		char str2[] = br.readLine().toCharArray();
		int len1 = str1.length;
		int len2 = str2.length;
		int idx	= 0;
		int cnt = 0;
		while(idx+len2 <= len1) {
			boolean flag = true;
			for(int i=0; i<len2; i++) {
				if(str1[idx+i] != str2[i]) {
					flag = false;
					break;
				}
			}
			if(flag) {
				cnt++;
				idx += len2;
			}else idx++;
		}
		
		System.out.print(cnt);
	}
}