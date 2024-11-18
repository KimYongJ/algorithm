//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1034
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
class Main{
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int Y			= Integer.parseInt(st.nextToken());	// Y(1<=50)
						  Integer.parseInt(st.nextToken());	// X(1<=50)
		String str[]	= new String[Y];
		HashMap<String, Integer> map = new HashMap<>();
		HashMap<String, Integer> zero= new HashMap<>();
		for(int y=0; y<Y; y++)
		{
			str[y] = br.readLine();
			map.put(str[y], map.getOrDefault(str[y], 0) + 1);
			if(!zero.containsKey(str[y]))
			{
				int cnt = 0;
				for(char c : str[y].toCharArray())
					if(c == '0')
						++cnt;
				zero.put(str[y], cnt);
			}
		}
		int K	= Integer.parseInt(br.readLine());	// 누르는횟수(0<=천)
		int res = 0;
		
		for(int y=0; y<Y; y++)
		{
			int zeroCnt = zero.get(str[y]);
			
			if(zeroCnt <= K && (K-zeroCnt) % 2 == 0)
				res = Math.max(res, map.get(str[y]));
			
		}
		System.out.print(res);
	}
}