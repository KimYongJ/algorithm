// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void change(boolean arr[], int idx) {
		arr[idx] = !arr[idx];
		if(idx-1>=0) arr[idx-1] = !arr[idx-1];
		if(idx+1<arr.length)arr[idx+1] = !arr[idx+1];
	}
	public static int solution(boolean v[][], boolean flag) {
		int cnt = 0;
		int len = v[0].length;
		boolean arr[] = v[0].clone();
		if(flag) {
			change(arr,0);
			cnt++;
		}
		for(int i=1; i<len; i++) {
			if(arr[i-1] != v[1][i-1]) {
				change(arr, i);
				cnt++;
			}
		}
		
		if(v[1][len-1] != arr[len-1]) {
			cnt = Integer.MAX_VALUE;
		}
		return cnt;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N			= Integer.parseInt(br.readLine());
		boolean v[][]	= new boolean[2][N];
		for(int i=0; i<2; i++) 
		{
			String str = br.readLine();
			for(int j=0; j<N; j++) 
			{
				v[i][j] = str.charAt(j) == '1';
			}
		}
		
		int res = Math.min(solution(v, true),solution(v, false));
		
		System.out.print(res == Integer.MAX_VALUE ? -1 : res);
	}
}