import java.io.*;
import java.util.*;

class Main{
	static int[] arr = new int[7];
	static int[] origin = new int[9];
	static int sum = 0;
	static boolean[] c = new boolean[100];
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<9; i++) 
			origin[i] = Integer.parseInt(br.readLine()); 
		
		back(0);
		
		Arrays.sort(arr);
		for(int x : arr)
			System.out.println(x);
	}
	public static void back(int depth) {
			if(depth == 7 && sum!=100) {
				sum = 0;
				for(int i=0; i<7;i++) 
					sum += arr[i];
				return;
			}
			
			for(int i=0; i<9; i++) {
				if(!c[origin[i]] && sum!=100) {
					arr[depth] = origin[i];
					c[origin[i]] = true;
					back(depth+1);
					c[origin[i]] = false;
				}
			}
	}
}