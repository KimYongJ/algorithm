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
				right 	= Math.min(X, Y);

		X = Math.pow(X, 2);
		Y = Math.pow(Y, 2);
		while(right - left >= 0.001) {
			double mid  = (left + right) / 2;
			double h1 	= Math.sqrt(X - Math.pow(mid,2));
			double h2 	= Math.sqrt(Y - Math.pow(mid,2));
			double c 	= (h1*h2) / (h1+h2);
			if(c < C)
				right = mid;
			else
				left = mid;
		}
		System.out.printf("%.3f",right);
	}
}