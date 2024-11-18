//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1034
import java.util.HashMap;
class Main{
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	static String readStr() throws Exception{
		StringBuilder sb = new StringBuilder();
		int c = System.in.read();
		while(c <= 32) {c = System.in.read();}
		while(c > 32) {
			sb.append((char)c);
			c = System.in.read();
		}
		return sb.toString();
	}
	public static void main(String[] args)throws Exception{
		HashMap<String, Integer> map, zero;
		map 	= new HashMap<>();
		zero	= new HashMap<>();
		int Y	= read();	// Y(1<=50)
				  read();	// 안씀
		String str[] = new String[Y];
		
		
		for(int y=0; y<Y; y++)
		{
			str[y] = readStr();
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
		int K	= read();	// 누르는횟수(0<=천)
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