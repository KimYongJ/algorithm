//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/16472
//1초 / 512MB
//요약 : 최대 사용 가능 알파뱃개수가 주어지면 그 알파벳 종류만 포함하는 가장긴 문자열의 길이 출력
//비슷한 백준 문제 : 22862(투포인터를 사용해 특정 조건 초과일 때만 s를 추가시키는 것이 같다)
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K		= Integer.parseInt(br.readLine());// 인식할 수 있는 알파벳 종류의 최대 개수(1<=26)
		int max		= 0;
		int cnt[]	= new int['z'+1];
		char arr[]	= br.readLine().toCharArray();// 문자열(1<=십만)
		int len		= arr.length;
		int s		= 0;
		int e		= 0;
		int k		= 0;
		
		while(e < len)
		{
			if(cnt[arr[e++]]++ == 0)
				++k;
			
			while(K<k)
				if(--cnt[arr[s++]] == 0)
					--k;
			
			max = Math.max(max, e - s);
		}
		System.out.print(max);
	}
}