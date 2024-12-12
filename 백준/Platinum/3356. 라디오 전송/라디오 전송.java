//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/3356
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int len		= Integer.parseInt(br.readLine());
		char ptn[]	= br.readLine().toCharArray();
		int fail[]	= new int[len];
		
		for(int i=1, j=0; i<len; i++)
		{
			while(0<j && ptn[i] != ptn[j])
				j = fail[j - 1];
			
			if(ptn[i] == ptn[j])
				fail[i] = ++j;
		}
		System.out.print(len - fail[len-1]);
	}
}