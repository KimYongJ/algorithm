// https://github.com/KimYongJ/algorithm


class Main{
	
	static int N, cnt, arr[];
	static boolean visit[];
	static StringBuilder sb;
	
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }


	public static void DFS(int start, int idx) {
			if(visit[idx]) {
				if(idx == start) {
					sb.append(idx).append('\n');
					cnt++;
				}
				return;
			}
			visit[idx] = true;
			DFS(start, arr[idx]);
		}
	public static void main(String[] args)throws Exception{
		N 			= read()+1;
		arr 		= new int[N];
		sb			= new StringBuilder();
		for(int i=1; i<N; i++)
			arr[i] = read();
		
		for(int i=1; i<N; i++) 
		{
			visit = new boolean[N];
			DFS(i,i);
		}

		System.out.println(cnt);
		System.out.print(sb);
	}
}