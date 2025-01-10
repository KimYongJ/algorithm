//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17611
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());	// 4<=십만
		int psumX[]	= new int[1_000_001];
		int psumY[]	= new int[1_000_001];
		int arr[][]	= new int[N][2];
		
		for(int i=0; i<N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0]	= Integer.parseInt(st.nextToken()) + 500_000;	// x좌표 (절대값 오십만)
			arr[i][1]	= Integer.parseInt(st.nextToken()) + 500_000;	// y좌표 (절대값 오십만)
		}
		
		for(int i=0; i<N; i++)
		{
			int x1 = arr[i][0];
			int y1 = arr[i][1];
			int x2 = arr[(i+1)%N][0];
			int y2 = arr[(i+1)%N][1];
			
			if(x1 == x2)	// x가 같다면 y가 다르기 때문에 
			{
				psumY[Math.min(y1, y2)]++;
				psumY[Math.max(y1, y2)]--;
			}
			else if(y1 == y2)	// y가 같다면 x가 다르기 때문에
			{
				psumX[Math.min(x1, x2)]++;
				psumX[Math.max(x1, x2)]--;
			}
		}
		
		int max = Math.max(psumX[0], psumY[0]);
		
		for(int i=1; i<1_000_001; i++)
		{
			psumY[i] += psumY[i-1];
			psumX[i] += psumX[i-1];
			max = Math.max(Math.max(max, psumY[i]),psumX[i]);
		}
		System.out.print(max);
	}
}