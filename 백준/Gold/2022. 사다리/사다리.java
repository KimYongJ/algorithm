// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		double	X 		= Double.parseDouble(st.nextToken()),
				Y 		= Double.parseDouble(st.nextToken()),
				C 		= Double.parseDouble(st.nextToken()),
				left 	= 0, 
				right 	= Math.min(X, Y),
				mid, h1, h2;

		X = Math.pow(X, 2);
		Y = Math.pow(Y, 2);
		
		while(left <= right) 
		{
			mid = (left + right) / 2;
			h1 	= Math.sqrt(X - Math.pow(mid,2));
			h2 	= Math.sqrt(Y - Math.pow(mid,2));
			
			if((h1*h2) / (h1+h2) < C)
				right = mid-0.001;
			
			else
				left = mid+0.001;
			
		}
		System.out.print(left);
	}
}