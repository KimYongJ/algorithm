// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();		
		String str = br.readLine();
		int len = str.length();
		char arr[] = new char[len];
		int cnt0 = 0;
		int cnt1 = 0;
		for(int i=0; i<len; i++) {
			arr[i] = str.charAt(i);
			if(arr[i] == '0') {
				cnt0++;
			}else {
				cnt1++;
			}
		}
		cnt0 /= 2;
		cnt1 /= 2;
		for(int i=0,j=len-1; i<len &&(cnt1>0||cnt0>0); i++,j--) 
		{
			if(cnt1 > 0 && arr[i] == '1') {
				arr[i] = '2';
				--cnt1;
			}
			if(cnt0 > 0 && arr[j] == '0') {
				arr[j] = '2';
				--cnt0;
			}
		}
		for(int i=0; i<len; i++) {
			if(arr[i] != '2') {
				sb.append(arr[i]);
			}
		}
		System.out.print(sb.toString());
	}
}