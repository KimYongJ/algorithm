// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int MAX, MIN;
	static int N, N_1, arr[] , base_cal[], cal[];
	static boolean visit[];

	public static int calculator(int flag, int a, int b) {
		if(flag == 0)		return a+b;
		else if(flag == 1)	return a-b;
		else if(flag == 2)	return a*b;
		else				return a/b;
	}
	public static void DFS(int depth) {
		if(depth == N_1) 
		{
			int num = arr[0];
			for(int i=0; i<N_1; i++) 
				num = calculator(cal[i], num, arr[i+1]);
			MAX = Math.max(MAX, num);
			MIN = Math.min(MIN, num);
			return;
		}
		
		for(int i=0; i<N_1; i++) 
		{
			if(!visit[i]) 
			{
				visit[i] = true;
				cal[depth] = base_cal[i];
				DFS(depth + 1);
				visit[i] = false;
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N 			= Integer.parseInt(br.readLine());
		N_1 		= N-1;
		arr 		= new int[N];  		  		// 숫자들을 받을 배열
		cal 		= new int[N_1];		  		// 연산자를 넣을 배열
		base_cal 	= new int[N_1];  			// 연산자 원본 배열
		visit 		= new boolean[N_1]; 		// 연산자들에 대해 DFS진행시 방문 체크할 배열
		MAX 		= Integer.MIN_VALUE;
		MIN 		= Integer.MAX_VALUE;
		st 			= new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++)
			arr[i] 	= Integer.parseInt(st.nextToken());
		
		st 			= new StringTokenizer(br.readLine());
		int idx 	= 0;
		
		for(int i=0; i<4; i++) // 4개의 연산자가 입력됨 
		{
			int operator = Integer.parseInt(st.nextToken());
			if(operator != 0) 
				for(int j=0; j<operator; j++) 
					base_cal[idx++] = i;
		}
		
		DFS(0);
		
		System.out.println(MAX);
		System.out.println(MIN);
	}

	// 빠른 입력을 위한 함수
//	static int read() throws Exception 
//	{
//	        int c, n = System.in.read() & 15;
//	        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
//	        return n;
//	} 

}