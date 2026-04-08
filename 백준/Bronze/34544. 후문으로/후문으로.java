//https://www.acmicpc.net/problem/34544
//1초 1024MB
//2 // 건물 수(1≤1,000)
//1 4 // -는 지하를 의미, 1층과 지하 1층 차이는 1임(-1,000≤Ai,Bi≤1,000 A≠0 , B≠0)
//-2 2
//답 : 7
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int sum = 1;
		
		while(N-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			int diff = y - x; // 지상 지상 이동시
			
			if(x>0 && y<0)diff++;
			if(x<0 && y>0)diff--;
			
			sum += diff;
		}
		
		if(sum <= 0)
			--sum;
		
		System.out.print(sum);
	}
}