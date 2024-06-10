// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int[]	team1,	team2;
	static int[]	tieArr, tieDummy;
	static int[]	winArr, winDummy;
	static int[]	loseArr,loseDummy;

	public static boolean backtracking(int round) {
		if(round == 15) 
		{
			for(int i=0; i<6; i++)
				if(tieArr[i] != tieDummy[i] ||
					winArr[i] != winDummy[i] || 
					loseArr[i]!= loseDummy[i]) 
					return false;
			return true;
		}
		
		int a = team1[round];
		int b = team2[round];
		
		// a가 이겼을 때
		if(winArr[a] > winDummy[a] && loseArr[b] > loseDummy[b]) {
			winDummy[a]++;
			loseDummy[b]++;
			if(backtracking(round + 1))
				return true;
			winDummy[a]--;
			loseDummy[b]--;
		}
		// b가 이겼을 때 
		if(winArr[b] > winDummy[b] && loseArr[a] > loseDummy[a]) {
			winDummy[b]++;
			loseDummy[a]++;
			if(backtracking(round + 1))
				return true;
			winDummy[b]--;
			loseDummy[a]--;
		}
		// 무승부일 때
		if(tieArr[a] > tieDummy[a] && tieArr[b] > tieDummy[b]) {
			tieDummy[a]++;
			tieDummy[b]++;
			if(backtracking(round + 1))
				return true;
			tieDummy[a]--;
			tieDummy[b]--;
		}
		
		return false;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder 	sb = new StringBuilder();
		StringTokenizer st;
		
		team1 = new int[15];
		team2 = new int[15];
		
		int teamIdx = 0;
		for(int i=0; i<5; i++)
			for(int j=i+1; j<6; j++) 
			{
				team1[teamIdx]	= i;
				team2[teamIdx++]= j;
			}
		
		int result;
		int tie, win, lose, ttie, twin, tlose, tcnt, maxtie;
		for(int r=0; r<4; r++) {
			st		= new StringTokenizer(br.readLine());
			result	= 1;
			ttie	= 0;
			twin	= 0;
			tlose	= 0;
			tcnt	= 0;
			maxtie	= 0;
			
			tieArr	= new int[6];
			winArr	= new int[6];
			loseArr = new int[6];
			tieDummy= new int[6];
			winDummy= new int[6];
			loseDummy=new int[6];
			
			for(int t=0; t<6; t++) {
				win		= Integer.parseInt(st.nextToken());
				tie		= Integer.parseInt(st.nextToken());
				lose	= Integer.parseInt(st.nextToken());
				ttie 	+= tie;
				twin	+= win;
				tlose	+= lose;
				tieArr[t] = tie;
				winArr[t] = win;
				loseArr[t] = lose;
				if(tie != 0)
					tcnt++;
				if(win + tie + lose > 5)  	// 각 한라인의 승+패+무는 5이하여야 함
					result = 0;
				if(maxtie < tie)			// 무승부의 최대개 수 구하기
					maxtie = tie;
			}
			if(
				(result != 0) &&
				(ttie % 2 == 1) ||						// 무승부의 총합이 홀수인 경우 불가
				(twin != tlose) ||  					// 승과 패는 같아야 한다.
				((30 - ttie) != (twin + tlose)) || 		// 총합 유효성 검증
				!backtracking(0)						// 전부 탐색
			  ) 		 
			{
				result = 0;
			}

			sb.append(result).append(' ');
		}
		
		System.out.print(sb);
	}
}