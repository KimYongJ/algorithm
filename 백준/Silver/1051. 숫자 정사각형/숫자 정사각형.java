//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int Y, X, max;
	static char[][] arr;
	public static int check(int y, int x) {
		char a, b, c, flag = arr[y][x];
		if(y+max < Y && x+max < X) {
			a = arr[y+max][x];
			b = arr[y][x+max];
			c = arr[y+max][x+max];
			if( flag == a && a== b && b== c) {
				return flag;
			}
		}
		return 0;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		Y = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		max = Math.min(Y, X)-1;
		arr = new char[Y][X];
		for(int y=0; y<Y; y++)
			arr[y] = br.readLine().toCharArray();
		
		while(max > 0) {
			
			for(int y=0; y<Y-max; y++) {
				for(int x=0; x<X-max; x++) {
					int num = check(y, x);
					if(num > 0) {
						max++;
						System.out.println(max*max);
						return;
					}
				}
			}
			
			max--;
			
		}
		System.out.println(1);
	}
}