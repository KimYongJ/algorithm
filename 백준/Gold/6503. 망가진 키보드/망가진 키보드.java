//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/6503
//1초 / 128MB
//요약 : 최대 m개의 서로 다른 문자로 이루어진 가장 긴 부분 문자열 길이 출력
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			int N = Integer.parseInt(br.readLine());
			if(N == 0)
				break;
			
			char arr[]	= br.readLine().toCharArray();
			int len		= arr.length;
			int cnt[]	= new int[128];
			int size	= 0;
			int s		= 0;
			int e		= 0;
			int max		= 0;
			
			while(e < len)
			{
				if(cnt[arr[e]] == 0)
					size++;
				
				cnt[arr[e++]]++;
				
				while(N < size)
				{
					if(--cnt[arr[s++]] == 0)
					{
						--size;
						break;
					}
				}
				max = Math.max(e - s, max);
			}
			sb.append(max).append('\n');
		}
		System.out.print(sb);
	}
}

