// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int E = Integer.parseInt(st.nextToken());
		int EM = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int MH = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int cnt = 0;

		while(true) {
			if(E>0)E--;
			else {
				if(EM>0)EM--;
				else break;
			}
			
			if(M>0)M--;
			else {
				if(EM > 0 || MH > 0) {
					if(EM >= MH) EM--;
					else MH--;
				}else break;
			}

			if(H>0)H--;
			else {
				if(MH>0)MH--;
				else break;
			}
				
			++cnt;
		}
		System.out.print(cnt);
	}
}