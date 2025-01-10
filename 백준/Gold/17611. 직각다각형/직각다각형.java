//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17611
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();	// 4<=십만
		int psumX[]	= new int[1_000_001];
		int psumY[]	= new int[1_000_001];
		int arr[][]	= new int[N][2];
		
		for(int i=0; i<N; i++)
		{
			arr[i][0]	= read() + 500_000;	// x좌표 (절대값 오십만)
			arr[i][1]	= read() + 500_000;	// y좌표 (절대값 오십만)
		}
		
		for(int i=0; i<N; i++)
		{
			int x1 = arr[i][0];
			int y1 = arr[i][1];
			int x2 = arr[(i+1)%N][0];
			int y2 = arr[(i+1)%N][1];
			
			if(x1 == x2)	// x가 같다면 y가 다르기 때문에 
			{
				psumY[Math.min(y1, y2)]++;
				psumY[Math.max(y1, y2)]--;
			}
			else if(y1 == y2)	// y가 같다면 x가 다르기 때문에
			{
				psumX[Math.min(x1, x2)]++;
				psumX[Math.max(x1, x2)]--;
			}
		}
		
		int max = Math.max(psumX[0], psumY[0]);
		
		for(int i=1; i<1_000_001; i++)
		{
			psumY[i] += psumY[i-1];
			psumX[i] += psumX[i-1];
			max = Math.max(Math.max(max, psumY[i]),psumX[i]);
		}
		System.out.print(max);
	}
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) {
        n = (n << 3) + (n << 1) + (c & 15);}
        return m ? ~n + 1 : n;
    }
}