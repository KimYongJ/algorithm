//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cnt = 0;
		int flag= 0;
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		while(N-->0) {
			int n = Integer.parseInt(st.nextToken());
			if(n==flag) {
				cnt++;
				flag = (flag+1)%3;
			}
		}
		System.out.print(cnt);
	}
}