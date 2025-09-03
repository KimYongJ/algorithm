//https://www.acmicpc.net/problem/13335
//1초 / 512MB
//10 100 100 // 트럭 개수1<=1000, 동시에 올라갈 수 있는 개수(1<=100), 다리의 최대 하중(10<=1000)
//10 10 10 10 10 10 10 10 10 10// 트럭의 무게 1<=10
////110

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());// 트럭 개수1<=1000
		int W = Integer.parseInt(st.nextToken());// 동시에 올라갈 수 있는 개수(1<=100)
		int L = Integer.parseInt(st.nextToken());// 다리의 최대 하중(10<=1000)
		int truck[] = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			truck[i] = Integer.parseInt(st.nextToken());
		
		int time = 0;
		int cnt = 0;// 현재 다리에 올라있는 트럭의 대수
		int sum = 0;// 다리위의 트럭의 무게 합
		int idx = 0;// 들어갈 트럭 인덱스
		Queue q = new Queue(N * W + 10);
		
		while(idx < N)
		{
			if(!q.isEmpty() && q.peek().outTime == time + 1)// 먼저 다리에 올라간 트럭이 나갈 시간이 되면 나간다.
			{
				Truck t = q.poll();
				cnt--;
				sum -= t.weight;
			}
			if(truck[idx] + sum <= L && cnt < W)// 트럭을 다리에 더 넣을 수 있을 때
			{
				int outTime = time + W + 1;
				int weight = truck[idx++];
				q.add(new Truck(outTime, weight));
				sum += weight;
				cnt++;
				time++;
			}
			else// 트럭을 다리에 더 넣을 수 없을 때는 나와야 하는 시간으로 time을 변경한다.
			{
				time = q.peek().outTime - 1;
			}
		}
		
		if(!q.isEmpty())
			time = q.peekLast().outTime;
		
		System.out.print(time);
	}
	static class Truck{
		int outTime;
		int weight;
		Truck(int o, int w){
			outTime = o;
			weight = w;
		}
	}
	static class Queue{
		Truck[] q;
		int s,e;
		Queue(int len){
			s = 0;
			e = -1;
			q = new Truck[len];
		}
		Truck poll() {return q[s++];}
		Truck peek() {return q[s];}
		Truck peekLast() {return q[e];}
		void add(Truck t) {q[++e] = t;}
		boolean isEmpty() {return q[s] == null;}
	}
}