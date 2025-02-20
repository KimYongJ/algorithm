//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/31565
//1초 1024MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		long total = getDiff(br.readLine(), br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());// 쓸 수 있는 최대 여우 0<=만
		int N = Integer.parseInt(st.nextToken());// 할수 있는 행동 수(0<=100)
		int dp[] = new int[T + 1];
		for(int i=1; i<=N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int f = Integer.parseInt(st.nextToken());
			int use = Integer.parseInt(st.nextToken()); // 내가 손해 보는 날
			int day = Integer.parseInt(st.nextToken()); // 내가 얻는 이득
			if(f == 3)
				day *=30;
			
			for(int j=T; j>=use; j--)
				dp[j] = Math.max(dp[j], dp[j-use] + day);
		}
		
		int max = 0;
		for(int i=0; i<=T; i++)
			max = Math.max(max, dp[i]);

		System.out.print(Math.abs(total - max));
	}
	public static long getDiff(String a, String b) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate date1 = LocalDate.parse(a.replaceAll(" ",""), formatter);
		LocalDate date2 = LocalDate.parse(b.replaceAll(" ",""), formatter);
		
		return ChronoUnit.DAYS.between(date1, date2);
	}
}
