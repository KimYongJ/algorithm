// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 과목수
		int M = Integer.parseInt(st.nextToken()); // 주어진 마일리지
		int arr[] = new int[N]; // 내가 써야할 최소한의 마일리지
		
		for(int i=0; i<N; i++) 
		{
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());	// 신청 사람 수
			int m = Integer.parseInt(st.nextToken());	// 해당 과목의 수강인원
			int per[] = new int[p];						// 각 사람이 수강신청한
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<p; j++) 
			{
				per[j] = Integer.parseInt(st.nextToken());
			}
			
			if(m > p) 
			{
				arr[i] = 1;
			}
			else 
			{
				Arrays.sort(per);
				arr[i] = per[p-m];
			}
		}
		Arrays.sort(arr);
		int cnt = 0;
		for(int i=0; i<N; i++) {
			if((M -= arr[i]) >= 0) {
				cnt++;
			}else break;
		}
		System.out.print(cnt);
	}
}