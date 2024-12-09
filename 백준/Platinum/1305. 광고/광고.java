//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1305
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[] str = br.readLine().toCharArray();
		int len = str.length;
		int []fail = new int[len];
		
		for(int i=1,j=0; i<len; i++)
		{
			while(0<j && str[i] != str[j])
				j = fail[j - 1];
			if(str[i] == str[j])
				fail[i] = ++j;
		}
		
		System.out.print(N - fail[len-1]);
	}
}