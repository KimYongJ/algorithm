//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/6503
//1초 / 128MB
//요약 : 최대 m개의 서로 다른 문자로 이루어진 가장 긴 부분 문자열 길이 출력
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
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
			HashMap<Character, Integer> map = new HashMap<>();
			
			int s = 0;
			int e = 0;
			int max = 0;
			while(e < len)
			{
				map.put(arr[e], map.getOrDefault(arr[e++], 0) + 1);
				
				while(N < map.size())
				{
					int value = map.getOrDefault(arr[s], 0);
					if(value == 1)
						map.remove(arr[s]);
					else
						map.put(arr[s],value - 1);
					++s;
				}
				
				max = Math.max(e - s, max);
			}
			sb.append(max).append('\n');
		}
		System.out.print(sb);
	}
}
