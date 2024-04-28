// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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
		int cnt = 0;
		int idx = 0;
		for(; idx<=lastIdx; idx++) {
			if(arr[idx] == num)
				break;
		}
		int newArr[] = new int[MAX];
		int newIdx = 0;
		for(int i=idx+1; i<=lastIdx; i++) {
			newArr[newIdx++] = arr[i]; 
		}
		for(int i=0; i<idx; i++) {
			newArr[newIdx++] = arr[i];
		}
		arr = newArr;
		return Math.min(idx, lastIdx-- -idx+1);
	}
}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Queue q = new Queue();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		for(int i=1; i<=N; i++)
			q.add(i);
		
		int cnt = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			cnt += q.getCnt(Integer.parseInt(st.nextToken()));
		}
		
		System.out.println(cnt);
	}
}