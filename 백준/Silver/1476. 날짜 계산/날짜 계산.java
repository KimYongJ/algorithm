//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1476
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int x1 = Integer.parseInt(st.nextToken());
		int y1 = Integer.parseInt(st.nextToken());
		int z1 = Integer.parseInt(st.nextToken());
		int x = 1;
		int y = 1;
		int z = 1;
		int year = 1;
		
		while(true) {
			if(x == x1 && y == y1 && z == z1)
			{
				System.out.println(year);
				return;
			}
			++x;
			++y;
			++z;
			++year;
			if(x == 16) x = 1;
			if(y == 29) y = 1;
			if(z == 20) z = 1;
		}
	}
}