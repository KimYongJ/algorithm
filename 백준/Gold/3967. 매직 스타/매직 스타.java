// https://github.com/kimyongj/algorithm

class Main{
	static final int line[][] = {
									{0,2,5,7},{0,3,6,10},
									{1,5,8,11},{4,6,9,11},
									{1,2,3,4},{7,8,9,10}
								};
	static final int rline[][] = {
									{0,4},{1,1},{1,3},{1,5},{1,7},{2,2},
									{2,6},{3,1},{3,3},{3,5},{3,7},{4,4}
								};
	static int 		arr[];
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
			// 숫자가 4개다 더했을 때 26이 아니거나, 26초과시 false 리턴
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
		Reader in 		= new Reader();
		char [][]result	= new char[5][9];
		arr				= new int[12];
		visit			= new boolean[12];
		useNumber		= new boolean[13];
		int idx = 0;
		for(int y=0; y<5; y++) 
		{
			for(int x=0; x<9; x++) 
			{
				result[y][x] = '.';
				char c = in.nextChar();
				if(c=='x') 
				{
					idx++;
				}
				else if(c != '.') 
				{
					visit[idx] 	= true;					// 해당 인덱스 방문처리
					arr[idx] 	= (c-64);				// 숫자를 담는다.
					useNumber[arr[idx]] = true;			// 해당 숫자는 이미 사용했다는 표식
					idx++;
				}
			}
		}
		
		backtracking(0);
		
		// 결과 세팅
		for(int i=0; i<12; i++) 
			result[rline[i][0]][rline[i][1]] = (char)(arr[i]+64);

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

// 리더
class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;
    char nextChar() throws Exception {
        byte c;
        while ((c = read()) < 32); // SPACE 분리라면 <=로, SPACE 무시라면 <로
        return (char)c;
    }
    byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}

