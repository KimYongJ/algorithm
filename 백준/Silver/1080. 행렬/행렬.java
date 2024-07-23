// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int Y = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int cnt = 0;
		boolean arr[][][] = new boolean[2][Y][X];
		for(int i=0; i<2; i++) 
		{
			for(int y=0; y<Y; y++)
			{
				String str = br.readLine();
				for(int x=0; x<X; x++) 
				{
					arr[i][y][x] = str.charAt(x) == '1';
				}
			}
		}
		for(int y=0; y<Y-2; y++) 
		{
			for(int x=0; x<X-2; x++) 
			{
				if(arr[0][y][x] != arr[1][y][x]) {
					cnt ++ ;
					for(int y1=y; y1<y+3; y1++) {
						for(int x1=x; x1<x+3; x1++) {
							arr[0][y1][x1] = !arr[0][y1][x1];
						}
					}
				}
			}
		}
		
		for(int y=0; y<Y; y++) {
			for(int x=0; x<X; x++) {
				if(arr[0][y][x] != arr[1][y][x]) {
					System.out.print(-1);
					return;
				}
			}
		}
		System.out.print(cnt);
	}
}