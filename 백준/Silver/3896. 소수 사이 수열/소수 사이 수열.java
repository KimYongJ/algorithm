//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/3896
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int prime[] = new int[100001];
		int len = 1299710;
		int idx = 0;
		boolean eratos[] = new boolean[len];
		eratos[1] = true;
		for(int i=2; i<len; i++)
		{
			if(!eratos[i])
			{
				prime[idx] = i;
				idx++;
				int start = i << 1;
				while(start < len)
				{
					eratos[start] = true;
					start += i;
				}
			}
		}
		
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0)
		{
			int n = Integer.parseInt(br.readLine());
			if(!eratos[n]) {
				sb.append(0);
			}else {
				int s = 0;
				int e = 100000;
				int resIdx = 0;
				while(s <= e)
				{
					int mid = (s + e) / 2;
					if(prime[mid] < n){
						s = mid + 1;
					}else {
						e = mid - 1;
						resIdx = mid;
					}
				}
				sb.append(prime[resIdx] - prime[resIdx-1]);
			}
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}