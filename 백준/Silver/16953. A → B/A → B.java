// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int num = b;
		int cnt = 0;
		while(true) {
			if(num<a || num==a) {
				if(num<a) cnt = -1;
				else cnt++;
				break;
			}
			if(num%2 == 0) {
				num/=2;
			}else if(num%10==1) {
				num/=10;
			}else {
				cnt = -1;
				break;
			}
			cnt++;
		}
		System.out.println(cnt);
	}	

}