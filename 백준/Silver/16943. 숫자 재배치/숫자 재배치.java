// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class Main{
	
	static int 		len, BASE, result;
	static Integer 	arr[];
	static boolean 	visit[];
	
	public static boolean backtracking(int depth, int sum) {
		if(depth == len) 
		{
			if(sum < BASE) 
			{
				result = sum;
				return true;
			}
			return false;
		}
	
		for(int i=0; i<len; i++)
			if(!visit[i]) 
			{
				if(depth==0 && arr[i] == 0) continue;
				visit[i] = true;
				if(backtracking(depth+1, sum*10 + arr[i]))return true;
				visit[i] = false;
			}
		
		return false;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String str1	= st.nextToken();
		BASE 		= Integer.parseInt(st.nextToken());
		len			= str1.length();
		result		= -1;
		arr			= new Integer[len];
		visit		= new boolean[len];
		
		for(int i=0; i<len; i++)
			arr[i] = str1.charAt(i)-'0';
		
		Arrays.sort(arr,Collections.reverseOrder());	// 숫자를 내림차순으로 정렬 후 백트레킹해서 첫번째 나오는 BASE보다 작은 값이 정답이 된다.
		
		// 빠른 연산을 위해 답을 찾으면 true리턴으로 재귀 종료
		backtracking(0,0);
		
		System.out.print(result);
	}
}