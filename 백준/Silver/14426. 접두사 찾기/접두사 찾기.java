//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/14426
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());//기준 문자열 N개 (1<=만)
		int M = Integer.parseInt(st.nextToken());//검사할 문자열 M개 (1<=만)
		HashSet<String> set = new HashSet<>();
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=1; j<=str.length(); j++) {
				set.add(str.substring(0,j));
			}
		}
		int res = 0;
		for(int i=0; i<M; i++) {
			if(set.contains(br.readLine()))
				res++;
		}
		System.out.print(res);
	}
}