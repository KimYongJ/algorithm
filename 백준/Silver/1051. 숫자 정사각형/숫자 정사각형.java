//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int Y, X, max;
	static char[][] arr;

	public static void main(String[] args)throws Exception{
		BufferedReader br 	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st 	= new StringTokenizer(br.readLine());
		Y 	= Integer.parseInt(st.nextToken());
		X 	= Integer.parseInt(st.nextToken());
		max = Math.min(Y, X);
		arr = new char[Y][X];
		for(int y=0; y<Y; y++)
			arr[y] = br.readLine().toCharArray();
		char a,b,c;
		while(max-- > 0) {
			for(int y=0; y<Y-max; y++) {
				for(int x=0; x<X-max; x++) {
					if(y+max < Y && x+max < X) {
						a = arr[y+max][x];
						b = arr[y][x+max];
						c = arr[y+max][x+max];
						if( arr[y][x] == a && a== b && b== c) {
							max+=1;
							System.out.println(max*max);
                            return;
						}
					}
				}
			}
		}
		System.out.println(1);
	}
}