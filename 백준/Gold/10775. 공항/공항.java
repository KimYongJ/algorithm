// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static int find(int gate[], int x) {
		if(gate[x] == x) return x;
		return gate[x] = find(gate, gate[x]);
	}
	public static void union(int gate[],int x, int y) {
		int x1 = find(gate,x);
		int y1 = find(gate,y);
		if(x1 > y1) {
			gate[x1] = y1;
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int G = Integer.parseInt(br.readLine()); // 게이트 수 1<=105
		int P = Integer.parseInt(br.readLine()); // 비행기 수 1<=105
		int cnt = 0;
		int gate[] = new int[G+1];
		
		for(int i=1; i<=G; i++) // 게이트 초기화
			gate[i] = i;
		
		for(int i=0; i<P; i++) 
		{
			int now = Integer.parseInt(br.readLine());
			int x = find(gate, now);
			
			if(x == 0)
				break;
			
			union(gate, x, x-1);
			
			cnt++;
		}
		System.out.print(cnt);
	}
}
