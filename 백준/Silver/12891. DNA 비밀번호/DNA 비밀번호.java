//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/12891
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		char C[] = {'A','C','G','T'};
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int len		= Integer.parseInt(st.nextToken());
		int P		= Integer.parseInt(st.nextToken());
		char str[]	= br.readLine().toCharArray();
		int ACGT[]	= new int[90];
		int CNT[]	= new int[90];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++)
			ACGT[C[i]] = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<P; i++)
			CNT[str[i]]++;
		
		int cnt = cal(ACGT, CNT, C);
		
		for(int i=P; i<len; i++)
		{
			CNT[str[i - P]]--;
			CNT[str[i]]++;
			cnt += cal(ACGT, CNT, C);
		}
		
		System.out.print(cnt);
	}
	private static int cal(int[] ACGT, int[] CNT, char[] ch) {
		for(char c : ch)
			if(ACGT[c] > CNT[c])
				return 0;
		return 1;
	}
	
	
}