//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int K = Integer.parseInt(br.readLine());
		int M = (int)Math.pow(2,(int)Math.ceil(Math.log(K) / Math.log(2)));
		int C = 0;
		sb.append(M).append(' ');
		if(K != M) {
			while(K != 0) {
				M /= 2;
				if(K>=M) {
					K-=M;
				}
				C++;
			}
		}
		sb.append(C);
		System.out.print(sb.toString());
	}
}