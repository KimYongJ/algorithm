// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	
	static final int line[][] = {
									{0,2,5,7},{0,3,6,10},
									{1,5,8,11},{4,6,9,11},
									{1,2,3,4},{7,8,9,10}
									};
	static int 		arr[];
	static char 	result[][];
	static boolean 	visit[], useNumber[];
	
	public static boolean validate() {
		int sum, cnt;
		for(int[] l : line) 
		{
			sum = 0;
			cnt = 0;
			for(int p : l)
				if(arr[p] > 0) 
				{
					sum += arr[p];
					cnt++;
				}
			
			if((cnt == 4 && sum != 26) || sum > 26) 
				return false;
		}
		return true;

	}
	public static boolean backtracking(int depth) {
		if(depth > 11)	// 끝 도달시 종료
			return true;
		
		if(visit[depth]) 					// 초기에 방문한 곳이면
			return backtracking(depth + 1);
		
		for(int i=1; i<13; i++) 
		{
			if(!useNumber[i]) 				// 사용하지 않은 숫자만 arr에 바인딩
			{
				useNumber[i] = true;
				arr[depth] = i;
				if(validate() && backtracking(depth + 1))// 가능성있을 때만 간다. 
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
		{
			for(int x=0; x<9; x++) 
			{
				sb.append(result[y][x]);
			}
			sb.append('\n');
		}

		System.out.print(sb);
	}
}