// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

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
		String str2 = st.nextToken();
		result 		= -1;
		len	   		= str1.length();
		BASE 		= Integer.parseInt(str2);
		len			= str1.length();
		arr			= new Integer[len];
		visit		= new boolean[len];
		
		for(int i=0; i<len; i++)
			arr[i] = str1.charAt(i)-'0';
		
		if(len <= str2.length()) {
			Arrays.sort(arr,Collections.reverseOrder());	// 숫자를 내림차순으로 정렬 후 백트레킹해서 첫번째 나오는 BASE보다 작은 값이 정답이 된다.
			if(len < str2.length()) {
				StringBuilder sb = new StringBuilder();
				
				for(int a : arr)sb.append(a);
				
				result = Integer.parseInt(sb.toString());
			}else {
				backtracking(0,0);				// 빠른 연산을 위해 답을 찾으면 true리턴으로 재귀 종료
			}
		}
		
		System.out.print(result);
	}
}