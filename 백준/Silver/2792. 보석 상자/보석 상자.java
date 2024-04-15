// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, M, jewel[], left, mid, right;
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N 		= Integer.parseInt(st.nextToken());
		M 		= Integer.parseInt(st.nextToken());
		jewel 	= new int[M];
		for(int i=0; i<M; i++) {
			jewel[i] = Integer.parseInt(br.readLine());
			right = Math.max(right, jewel[i]);
		}
		left = 1;
		while(left < right) {
			mid = (left + right) / 2;
			if(check(mid)) {
				right = mid;
			}else {
				left = mid+1;
			}
		}
		System.out.println(right);
	}
	public static boolean check(int mid) {
		int people = 0;
		for(int j : jewel) {
			people += j/mid;
			people += j%mid == 0 ? 0 : 1;
			if(people > N)
				return false;
		}
		return true;
	}
}