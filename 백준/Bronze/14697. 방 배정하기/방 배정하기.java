//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14697

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a[] = new int[3];
		a[0] = Integer.parseInt(st.nextToken());
		a[1] = Integer.parseInt(st.nextToken());
		a[2] = Integer.parseInt(st.nextToken());
		int target = Integer.parseInt(st.nextToken());
		
		Arrays.sort(a);
		
		int lenA = (target / a[0]) + 1;
		int lenB = (target / a[1]) + 1;
		int lenC = (target / a[2]) + 1;
		
		for(int i=0; i<lenA; i++)
			for(int j=0; j<lenB; j++)
				for(int z=0; z<lenC; z++)
				{
					int sum = i*a[0] + j*a[1] + z*a[2];
					if(target == sum) {
						System.out.print(1);
						return;
					}
					else if(target < sum)
						break;
				}

		
		System.out.print(0);
	}
}