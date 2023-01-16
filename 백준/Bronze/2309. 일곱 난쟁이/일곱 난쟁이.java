import java.io.*;
import java.util.*;

class Main{

	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] origin = new int[9];
		int sum = 0;
		for(int i=0; i<9; i++) {
			origin[i] = Integer.parseInt(br.readLine());
			sum += origin[i];
		}
		
		int[] result = new int[7];
		
		
		int cnt = 0;
		for(int i=0;i<8;i++) 
			for(int j=0; j<9; j++) {
				if(i==j) continue;
				if(sum-origin[i]-origin[j] == 100) {
					for(int x=0; x<9; x++) 
						if(x!=i && x!=j)
							result[cnt++] = origin[x];
					Arrays.sort(result);
					for(int x : result)
						System.out.println(x);
					return;
				}
			}
					

		
	}

}