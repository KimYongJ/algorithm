// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	
	
	static int 		arr[];
	static char 	result[][];
	static boolean 	visit[], useNumber[];
	
	public static boolean validate() {
		return arr[0]+arr[2]+arr[5]+arr[7] 	== 26 &&
		       arr[0]+arr[3]+arr[6]+arr[10] == 26 &&
		       arr[1]+arr[5]+arr[8]+arr[11] == 26 &&
		       arr[4]+arr[6]+arr[9]+arr[11] == 26 &&
		       arr[1]+arr[2]+arr[3]+arr[4] 	== 26 &&
		       arr[7]+arr[8]+arr[9]+arr[10] == 26;
	}
	public static boolean backtracking(int depth) {
		if(depth > 11) 						// 끝에 도달시 유효한지 체크
			return validate();
		
		if(visit[depth]) 					// 초기에 방문한 곳이면
			return backtracking(depth + 1);
		
		for(int i=1; i<13; i++) 
		{
			if(!useNumber[i]) 				// 사용하지 않은 숫자만 arr에 바인딩
			{
				useNumber[i] = true;
				arr[depth] = i;
				if(backtracking(depth + 1))	// true리턴시 바로 종료 
					return true;
				arr[depth] = 0;
				useNumber[i] = false;
			}
		}
		
		return false;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		result		= new char[5][9];
		arr			= new int[12];
		visit		= new boolean[12];
		useNumber	= new boolean[13];
		int idx = 0;
		for(int y=0; y<5; y++) 
		{
			String str = br.readLine();
			for(int x=0; x<9; x++) 
			{
				result[y][x] = '.';
				char c = str.charAt(x);
				if(c=='x') 
				{
					idx++;
				}
				else if(c != '.') 
				{
					visit[idx] 	= true;					// 해당 인덱스 방문처리
					arr[idx] 	= (str.charAt(x)-64);	// 숫자를 담는다.
					useNumber[arr[idx]] = true;			// 해당 숫자는 이미 사용했다는 표식
					idx++;
				}
			}
		}
		
		backtracking(0);
		
		// 결과 출력
		result[0][4] = (char) (arr[0]+64);
		result[1][1] = (char) (arr[1]+64);
		result[1][3] = (char) (arr[2]+64);
		result[1][5] = (char) (arr[3]+64);
		result[1][7] = (char) (arr[4]+64);
		result[2][2] = (char) (arr[5]+64);
		result[2][6] = (char) (arr[6]+64);
		result[3][1] = (char) (arr[7]+64);
		result[3][3] = (char) (arr[8]+64);
		result[3][5] = (char) (arr[9]+64);
		result[3][7] = (char) (arr[10]+64);
		result[4][4] = (char) (arr[11]+64);
		StringBuilder sb = new StringBuilder();
		for(int y=0; y<5;  y++)
			sb.append(String.valueOf(result[y])).append('\n');
		System.out.print(sb);
	}
}