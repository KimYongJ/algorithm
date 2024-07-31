// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
class Node{
	int idx,num; Node(int idx, int num){this.idx=idx; this.num=num;}
}
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Integer> list = new ArrayList<>();
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			list.add(Integer.parseInt(st.nextToken()));
		
		int S = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N && S>0; i++) 
		{
			int maxNum = 0, maxIdx = 0;
			for(int j=i,s=S; j<N && s>=0; j++,s--) {
				if(maxNum < list.get(j)) {
					maxNum = list.get(j);
					maxIdx = j;
				}
			}
			S -= (maxIdx - i);
			list.remove(maxIdx);
			list.add(i,maxNum);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int a : list) 
			sb.append(a).append(' ');
		System.out.print(sb.toString());
	}
}