//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/3040
class Main{
	
	static int map[] = new int[9];
	static int res[] = new int[7];
	
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static boolean Bruteforce(int idx, int depth, int sum) {
		if(depth == 7)
			return sum == 100;
		
		for(int i=idx; i<9; i++)
		{
			res[depth] = map[i];
			if(Bruteforce(i + 1, depth + 1, sum + map[i]))
				return true;
		}
		return false;
	}
	
	public static void main(String[] args)throws Exception{
		for(int i=0; i<9; i++)
			map[i] = read();
		
		Bruteforce(0, 0, 0);
		
		StringBuilder sb = new StringBuilder();
		for(int r : res)
			sb.append(r).append('\n');
		System.out.print(sb.toString());
	}
}