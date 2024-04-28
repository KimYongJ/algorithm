// https://github.com/KimYongJ/algorithm

class Queue{
	int lastIdx;
	int arr[];
	int MAX = 51;
	public Queue() {
		arr 		= new int[MAX];
		lastIdx		= -1;
	}
	public int add(int num) {
		return arr[++lastIdx] = num;
	}
	public int getCnt(int num) {
		int idx = 0;
		for(; idx<=lastIdx; idx++) {
			if(arr[idx] == num)
				break;
		}
		int newArr[] = new int[MAX];
		int newIdx = 0;
		for(int i=idx+1; i<=lastIdx; i++)
			newArr[newIdx++] = arr[i]; 
		
		for(int i=0; i<idx; i++)
			newArr[newIdx++] = arr[i];
		
		arr = newArr;
		return Math.min(idx, lastIdx-- -idx+1);
	}
}
class Main{
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		Queue q = new Queue();
		int N 	= read();
		int M 	= read();
		int cnt = 0;
		
		for(int i=1; i<=N; i++)
			q.add(i);
		
		for(int i=0; i<M; i++)
			cnt += q.getCnt(read());
		
		System.out.println(cnt);
	}
}