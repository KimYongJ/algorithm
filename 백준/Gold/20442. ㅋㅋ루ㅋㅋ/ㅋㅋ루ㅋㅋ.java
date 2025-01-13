//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/20442
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br	= new BufferedReader(new InputStreamReader(System.in));
		Queue k_cnt_left	= new Queue();
		Queue k_cnt_right	= new Queue();
		char str[]	= br.readLine().toCharArray();
		int len		= str.length;

		for(int L=0, R=len-1, lk=0, rk=0; L<len; L++, R--)
		{
			if(str[L] == 'K')lk++;
			else k_cnt_left.add(lk);
			if(str[R] == 'K')rk++;
			else k_cnt_right.add(rk);
		}
		
		int max = 0;
		int s	= 0;
		int e	= k_cnt_right.size()-1;// left와 right 리스트는 길이가 같을 수 밖에 없다. R개수만큼 이니까.
		while(s <= e)
		{
			int leftK	= k_cnt_left.peek();
			int rightK	= k_cnt_right.peek();
			max = Math.max(max, e - s + 1 + Math.min(leftK, rightK)*2);
			
			if(leftK < rightK)
			{
				s++;
				k_cnt_left.pop();
			}
			else
			{
				e--;
				k_cnt_right.pop();
			}
		}
		System.out.print(max);
	}
}
class Queue{
	int s = 0;
	int e = -1;
	int arr[] = new int[3_000_001];
	int size = 0;
	public int size() {return size;}
	public void add(int cnt) {
		arr[++e] = cnt;
		++size;
	}
	public int peek() {
		return arr[s];
	}
	public int pop() {
		if(size == 0)
			return 0;
		--size;
		return arr[s++];
	}
}