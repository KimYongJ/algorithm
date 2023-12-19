// https://github.com/KimYongJ/algorithm

class Main{

	static int N, M, START, CNT;
	static int like, dislike;
	static int manlike[];
	static int dislikeChannel[];
	static boolean visitChannel[];
	
	public static int read() throws Exception{
		int c, n = System.in.read() & 15;
		boolean negative = n == 13;
		if(negative) n = System.in.read() & 15;
		while((c = System.in.read()) > 32) n = (n<<3) + (n<<1) + (c & 15);
		if(c == 13) System.in.read();
		return negative?~n+1:n;
	}
	
	public static void DFS(int channel) {
		if(dislikeChannel[channel]==0) // end condition
			return;
		if(visitChannel[channel]) { // cycle
			CNT = -1;
			return;
		}
		visitChannel[channel] = true;
		CNT++;
		DFS(manlike[ dislikeChannel[channel] ]);
	}

	public static void main(String[] args)throws Exception{
		N 				= read();
		M 				= read();
		START 			= read();
		manlike 		= new int[N+1];
		visitChannel 	= new boolean[M+1];
		dislikeChannel 	= new int[M+1];

		
		for(int i=1; i<=N; i++) {
			like 		= read();
			dislike 	= read();
			manlike[i] 	= like;
			if(dislikeChannel[dislike] == 0)
				dislikeChannel[dislike] = i; // 가장 어린 사람을 넣음
		}
		
		DFS(START);
		
		System.out.println(CNT);
	}
}