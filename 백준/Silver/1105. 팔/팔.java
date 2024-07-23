// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		char a[] = st.nextToken().toCharArray();
		char b[] = st.nextToken().toCharArray();
		if(a.length != b.length) {
			System.out.print(0);
		}else {
			int cnt = 0;
			for(int i=0; i<a.length; i++) {
				if(a[i] == b[i]) {
					if(a[i] == '8') cnt++;
				}else break;
			}
			System.out.print(cnt);
		}
	}
}