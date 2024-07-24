// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int Y	= Integer.parseInt(st.nextToken());
		int X	= Integer.parseInt(st.nextToken());
		int res = 0;
		boolean visit[][] = new boolean[Y][X];
		for(int y=0; y<Y; y++) {
			String str = br.readLine();
			for(int x=0; x<X; x++) {
				visit[y][x] = str.charAt(x) == '1';
			}
		}
		
		for(int y=Y-1; y>=0; y--) {
			for(int x=X-1; x>=0; x--) {
				if(visit[y][x]) // 해당 칸이 뒷면(1)이라면 
				{
					res++;
					for(int y1=0; y1<=y; y1++) {
						for(int x1=0; x1<=x; x1++) {
							visit[y1][x1] = !visit[y1][x1];
						}
					}
				}
			}
		}
		System.out.print(res);
	}
}