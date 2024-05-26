// https://github.com/kimyongj/algorithm

class Main{
	
	static int LEN, TIME, MAX;
	static int map[];
	
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void DFS(int time, int idx, int sum) {
		if(idx >= LEN || time == TIME) 
		{
			MAX = Math.max(MAX, sum);
			return;
		}
		
		DFS(time+1, idx+1, sum + map[idx]);
		DFS(time+1, idx+2, sum/2 + map[idx+1]);
	}
	public static void main(String[] args)throws Exception{
		LEN		= read();
		TIME 	= read();
		map 	= new int[LEN+1];
		
		for(int i=0; i<LEN; i++)
			map[i] = read();
		
		DFS(0, 0, 1);
		
		System.out.print(MAX);
	}
}