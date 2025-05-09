//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/11375
//2초 256MB
class Main{
	
	static int N;				// 직원 수
	static int M;				// 일의 최대 수
	static int[] job;			// 각일에 대해 현재 매칭된 사람을 담을 배열, idx가 일의 번호, value가 매칭된 사람
	static boolean[] jobVisit;	// 인덱스가 일의 번호를 의미하며, 해당 일을 방문 해서 체크했는지를 저장
	static int[][]adList;
	
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		N		= in.nextInt();// 직원 수(1<=1,000)
		M		= in.nextInt();// 일의 수(1<=1,000)
		adList	= new int[N + 1][];
		job		= new int[M + 1];
		
		for(int i=1; i<=N; i++)
		{
			int j = in.nextInt();
			
			adList[i] = new int[j];
			
			while(--j>=0)
				adList[i][j] = in.nextInt();
		}
		
		int cnt = 0;

		for(int person=1; person<=N; person++)
		{
			jobVisit = new boolean[M + 1];
			if(dfs(person))
				++cnt;
		}
		System.out.print(cnt);
	}
	static boolean dfs(int person)
	{
		for(int jobNumber : adList[person])
		{
			if(jobVisit[jobNumber])
				continue;
			
			jobVisit[jobNumber] = true;
			
			if(job[jobNumber] == 0 || dfs(job[jobNumber]))
			{
				job[jobNumber] = person;
				return true;
			}
		}
		return false;
	}
}

//5 5		// 직원 수(1<=1,000), 일의 수(1<=1,000)
//2 1 2	// 각 직원이 할 수 있는 일의 번호
//1 1
//2 2 3
//3 3 4 5
//1 1
//// 답
//4


class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;
    
    int nextInt() throws Exception {
        int n = 0;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32) { if (size < 0) return -1; }
        if (c == 45) { c = read(); isMinus = true; }
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber(c = read()));
        return isMinus ? ~n + 1 : n;
    }

    boolean isNumber(byte c) {
        return 47 < c && c < 58;
    }

    byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}
