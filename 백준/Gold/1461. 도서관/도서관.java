// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
class Main{
	
	static int N, M;
	
	public static int canZero(ArrayList<Integer> list) {
		int sum = 0;
		int m = M;
		for(int i=list.size()-1; i>=0; i--) {
			if(m == M) {
				sum += list.get(i)<<1;
			}
			if(--m == 0) {
				m = M;
			}
		}
		return sum;
	}
	public static int cannotZero(ArrayList<Integer> list) {
		int size = list.size();
		int sum = 0;
		int m = 0;
		int i = 0;
		if(size > M) {
			
			i = (size % M);
			if(i != 0)
				sum = list.get(i-1)<<1;
		}else {
			return list.get(size-1);
		}
		for(; i<size; i++) {
			if(++m == M) {
				m = 0;
				sum += list.get(i)<<1;
			}
		}
		// 마지막은 0으로 갈 필요 없으므로 뺌
		return sum - list.get(size-1);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br		= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st		= new StringTokenizer(br.readLine());
		ArrayList<Integer> pos	= new ArrayList<>();
		ArrayList<Integer> neg	= new ArrayList<>();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) 
		{
			int n = Integer.parseInt(st.nextToken());
			if(n < 0)
				neg.add(Math.abs(n));
			else
				pos.add(n);
		}
		int posSize = pos.size();
		int negSize = neg.size();
		if(posSize > 0)
			Collections.sort(pos);
		if(negSize > 0)
			Collections.sort(neg);
		
		int sum = 0;
		if(posSize >0 && negSize > 0) {
			if(pos.get(pos.size()-1) > neg.get(neg.size()-1)) 
				sum += canZero(neg) + cannotZero(pos);
			else
				sum += canZero(pos) + cannotZero(neg);
		}else {
			if(posSize > 0)
				sum += cannotZero(pos);
			if(negSize > 0)
				sum += cannotZero(neg);
		}
		System.out.print(sum);
	}
}