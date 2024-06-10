// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int[] team1,team2,tieArr,winArr,loseArr;

	public static boolean backtracking(int round) {
		if(round == 15) 
		{
			for(int i=0; i<6; i++)
				if(tieArr[i] != 0 || winArr[i] != 0 || loseArr[i]!= 0) 
					return false;
			return true;
		}
		
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
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder 	sb = new StringBuilder();
		StringTokenizer st;
		
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
			st		= new StringTokenizer(br.readLine());
			result	= 1;
			total	= 0;
			for(int t=0; t<6; t++) 
			{
				winArr[t]	=	Integer.parseInt(st.nextToken());
				tieArr[t]	=	Integer.parseInt(st.nextToken());
				loseArr[t]	=	Integer.parseInt(st.nextToken());
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