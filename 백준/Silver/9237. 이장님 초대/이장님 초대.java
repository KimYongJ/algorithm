// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N	= Integer.parseInt(br.readLine());
		int max = 0;
		int day = 0;
		int counting[] = new int[1000001];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++)
			counting[Integer.parseInt(st.nextToken())]++;
		
		for(int i=1000000; i>=1; i--) 
			if(counting[i] > 0) 
				max = Math.max(max, (day+=counting[i])+i);
		
		System.out.print(max + 1); // 이장 초대는 하루 뒤이므로
	}
}