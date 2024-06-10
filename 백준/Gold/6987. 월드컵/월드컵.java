// https://github.com/kimyongj/algorithm

class Main{
	
	static int[] team1,team2,tieArr,winArr,loseArr;

	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static boolean backtracking(int round) {
		if(round == 15) 
			return true;
		
		int a = team1[round];
		int b = team2[round];
		
		// a가 이겼을 때
		if(winArr[a] > 0 && loseArr[b] > 0) {
			winArr[a]--;
			loseArr[b]--;
			if(backtracking(round + 1))
				return true;
			winArr[a]++;
			loseArr[b]++;
		}
		// b가 이겼을 때 
		if(winArr[b] > 0 && loseArr[a] > 0) {
			winArr[b]--;
			loseArr[a]--;
			if(backtracking(round + 1))
				return true;
			winArr[b]++;
			loseArr[a]++;
		}
		// 무승부일 때
		if(tieArr[a] > 0 && tieArr[b] > 0) {
			tieArr[a]--;
			tieArr[b]--;
			if(backtracking(round + 1))
				return true;
			tieArr[a]++;
			tieArr[b]++;
		}
		return false;
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		team1	= new int[15];
		team2	= new int[15];
		tieArr	= new int[6];
		winArr	= new int[6];
		loseArr = new int[6];
		
		int result, teamIdx=0, total;
		for(int i=0; i<5; i++)
			for(int j=i+1; j<6; j++) 
			{
				team1[teamIdx]	= i;
				team2[teamIdx++]= j;
			}
		
		
		for(int r=0; r<4; r++) 
		{
			result	= 1;
			total	= 0;
			for(int t=0; t<6; t++) 
			{
				winArr[t]	= read();
				tieArr[t]	= read();
				loseArr[t]	= read();
				if(tieArr[t] + winArr[t] + loseArr[t] > 5)  	// 각 한라인의 승+패+무는 5이하여야 함
					result = 0;
				total += tieArr[t] + winArr[t] + loseArr[t];
			}
			if( total != 30 || (result != 0 && !backtracking(0))) 		 
				result = 0;
			sb.append(result).append(' ');
		}
		
		System.out.print(sb);
	}
}