//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/20440
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Pos{int s,e;Pos(int s, int e){this.s=s;this.e=e;}}

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		TreeSet<Integer> set = new TreeSet<>();
		int N		= Integer.parseInt(br.readLine());
		Pos[] pos	= new Pos[N];
		
		for(int i=0; i<N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			pos[i] = new Pos(s,e);
			set.add(s);
			set.add(e);
		}
		int idx[] = new int[set.size()];
		int index = 0;
		for(int s : set)
			idx[index++] = s;
		
		int len		= set.size();
		int psum[]	= new int[len+1];
		
		for(Pos p : pos)
		{
			int s = binarySearch(idx, p.s);
			int e = binarySearch(idx, p.e);
			psum[s]++;
			if(p.s != p.e)
				psum[e]--;
		}

		int max		= psum[0];
		int start	= -1;
		int end		= -1;
		
		for(int i=1; i<=len; i++)
			max = Math.max(max, psum[i] += psum[i-1]);

		for(int i=0; i<=len; i++)
			if(psum[i] == max && start < 0)
			{
				start = i;
				end = i;
				max = psum[i];
			}
			else if(psum[i] == max && i-1 == end)
				end = i;

		
		sb.append(max).append('\n').append(idx[start]).append(' ').append(idx[end+1]);

		System.out.print(sb);
	}
	public static int binarySearch(int arr[], int target) {
		int s = 0;
		int e = arr.length-1;
		int r = 0;
		while(s<=e)
		{
			int mid = (s + e) >> 1;
			if(target <= arr[mid])
			{
				r = mid;
				e = mid - 1;
			}
			else s = mid + 1;
		}
		return r;
	}
}