//https://www.acmicpc.net/problem/14402
//2초 512MB
//7 // 출입기록 개수 (1<=100,000)
//nein + // 이름과 나간지 들어간지 체크 +가 들어간것
//nein -
//nein -
//nein -
//nein +
//nein +
//nein +
//답 : 5
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashMap<String, Integer> map = new HashMap<>();
		int N = Integer.parseInt(br.readLine());
		int cnt = 0;
		
		for(int i=0; i<N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			
			if("-".equals(st.nextToken()))
			{
				int num = map.getOrDefault(name,0);
				if(num == 0) cnt++;
				else map.put(name, num - 1);
			}
			else
				map.put(name, map.getOrDefault(name, 0) + 1);
		}
		
		for(int v : map.values())
			cnt += v;

		System.out.print(cnt);
	}
}