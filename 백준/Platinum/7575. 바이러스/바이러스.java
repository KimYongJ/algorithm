//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/7575

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, K;
	static int arr[][];
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N	= Integer.parseInt(st.nextToken());	// 2<=100, 감염된 프로그램 개수
		K	= Integer.parseInt(st.nextToken());	// 코드 최소 길이 4<=1000
		arr	= new int[N][];
		
		for(int i=0; i<N; i++)
		{
			int len	= Integer.parseInt(br.readLine());
			arr[i]	= new int[len];
			st		= new StringTokenizer(br.readLine());
			for(int j=0; j<len; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		int len = arr[0].length - K;
		LOOP:
		for(int idx=0; idx<=len; idx++)
		{
			int forward[]	= getCode(idx);
			int reverse[]	= getReverse(forward);
			int fail1[]		= getFail(forward);
			int fail2[]		= getFail(reverse);
			
			for(int i=0; i<N; i++)
				if(!KMP(arr[i], forward,fail1) && !KMP(arr[i], reverse, fail2))
					continue LOOP;
			
			System.out.print("YES");
			return;
		}
		System.out.print("NO");
	}
	public static boolean KMP(int text[], int pattern[], int fail[]) {
		int len = text.length;
		
		for(int i=0, j=0; i<len; i++)
		{
			while(0<j && text[i] != pattern[j])
				j = fail[j - 1];
			
			if(text[i] == pattern[j])
			{
				if(j == K - 1)
					return true;
				else ++j;				
			}
		}
		
		return false;
	}
	// KMP알고리즙을 위해 fail 배열을 만든다.
	public static int[] getFail(int[] origin) {
		int[] fail = new int[K];
		for(int i=1, j=0; i<K; i++)
		{
			while(0<j && origin[i] != origin[j])
				j = fail[j - 1];
			
			if(origin[i] == origin[j])
				fail[i] = ++j;
		}
		return fail;
	}
	// 바이러스는 자신의 코드를 반대로 기입한다고 했으므로 반대도 만들어준다.
	public static int[] getReverse(int[] origin) {
		int reverse[] = new int[K];
		for(int i=0; i<K; i++)
			reverse[i] = origin[K-i-1];
		return reverse;
	}
	// 첫번째 프로그램 코드로 부터 바이러스 코드로 추정되는 부분을 가져온다.
	public static int[] getCode(int s) {
		int res[] = new int[K];
		for(int i=0; i<K; i++)
			res[i] = arr[0][s++];
		return res;
	}
}