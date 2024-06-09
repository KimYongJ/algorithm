// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{

	static int N;
	static int arr[];
	
	public static boolean check(int len) {
        for (int i = 1; i <= len / 2; i++) 
        {
            for (int j = 0; j <= len - 2 * i; j++) 
            {
                boolean same = true;
                for (int k = 0; k < i; k++)
                {
                    if (arr[j + k] != arr[j + i + k]) 
                    {
                        same = false;
                        break;
                    }
                }
                if (same) return false;
            }
        }
        return true;
	}
	public static boolean backtracking(int depth, int before) {
		if(depth == N) 
			return true;
		
		for(int i=1; i<=3; i++) 
			if(i != before) 
			{
				arr[depth] = i;
				if(check(depth + 1) && backtracking(depth + 1, i))
					return true;
			}
		
		return false;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N	= Integer.parseInt(br.readLine());
		arr = new int[N];
		backtracking(0,-1);
		
		StringBuilder sb = new StringBuilder();
		for(int a : arr)
			sb.append(a);
		System.out.print(sb);
	}
}