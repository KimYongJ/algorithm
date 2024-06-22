// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		
		for(int i=1; i<=N; i++) {
			for(int j=N-i; j>0; j--) {
				bw.write(' ');
			}
			for(int j=0; j<i*2-1; j++) {
				bw.write('*');
			}
			bw.write('\n');
		}
		
		for(int i=1; i<N; i++) {
			for(int j=0; j<i; j++) {
				bw.write(' ');
			}
			for(int j=0; j<(N-i)*2-1; j++) {
				bw.write('*');
			}
			bw.write('\n');
		}

		bw.flush();
		bw.close();
		br.close();
	}
}