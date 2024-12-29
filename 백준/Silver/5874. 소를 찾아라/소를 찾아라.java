//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/5874
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
class Main{
	public static void main(String[] args)throws Exception{
		ArrayList<Integer> list = new ArrayList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] str	= br.readLine().toCharArray();
		int len		= str.length;
		int[] psum	= new int[len];
		int rcnt	= 0;
		int lcnt	= 0;
		for(int i=0; i<len; i++)
		{
			if(str[i] == ')')
			{
				if(2<=++rcnt)
					psum[i] = 1;
				lcnt = 0;
			}
			else
			{
				rcnt = 0;
				if(2<=++lcnt)
					list.add(i);
			}
		}
		
		for(int i=1; i<len; i++)
			psum[i] += psum[i-1];
		
		int sum = 0;
		
		for(int idx : list)
			sum += psum[len-1] - psum[idx];
		
		System.out.print(sum);
	}
}