//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/3745
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
    private static int read() throws Exception{
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        boolean minus = false;
        if (c == '-') {
            minus = true;
            c = System.in.read();
        }
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);
        if (minus) return -val;
        return val;
    }
	public static int getIdx(int LIS[], int target, int len) {
		int idx = 0;
		int s	= 0;
		int e	= len - 1;
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			if(LIS[mid] == target)
				return mid;
			if(LIS[mid] < target)
				s = mid + 1;
			else
			{
				idx = mid;
				e	= mid - 1;
			}
		}
		return idx;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			StringTokenizer st;
			try {
			st = new StringTokenizer(br.readLine());
			}catch(Exception e) {
				break;
			}
			String str = st.nextToken();
			if(str == null || str.length() == 0)
				break;
			
			int N		= Integer.parseInt(str);	// 1<=100000
			int len		= 1;
			int arr[]	= new int[N];
			int LIS[]	= new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++)
				arr[i] = Integer.parseInt(st.nextToken());
			
			LIS[0] = arr[0];
			
			for(int i=1; i<N; i++) {
				if(LIS[len - 1] < arr[i])
					LIS[len++] = arr[i];
				else
					LIS[getIdx(LIS, arr[i], len)] = arr[i];
			}
			
			sb.append(len).append('\n');
		}
		System.out.print(sb.toString());
	}
}