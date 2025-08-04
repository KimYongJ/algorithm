//https://www.acmicpc.net/problem/31477
//1초 1024MB
//7// 방의 수 (2<=100,000)
//1 2 3 //A,B,V가 주어지며 A,B는 방번호(1<=N), V는 두께(1<=1,000)
//1 3 4
//2 4 2
//2 5 2
//3 6 1
//3 7 2
//1번방은 양갈래가 묶인방이며 덩굴을 자르기 위해 필요한 힘의 합의 최솟값을 출력한다.
//답6
import java.util.ArrayList;
import java.util.List;

class Main{
	
	static int N;
	static List<Node> adList[];
	
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		N = in.nextInt();
		adList = new ArrayList[N + 1];

		for(int i=0; i<=N; i++)
			adList[i] = new ArrayList<>();
		
		for(int i=1; i<N; i++)
		{
			int a = in.nextInt();
			int b = in.nextInt();
			int v = in.nextInt();
			adList[a].add(new Node(b,v));
			adList[b].add(new Node(a,v));
		}
		
		System.out.print(dfs(1, 0));

	}
	static int dfs(int now, int parent) {
		int sum = 0;
		
		for(Node next : adList[now])
		{
			if(parent == next.next)
				continue;
			
			sum += Math.min(next.v, dfs(next.next, now));
		}
		// 리프노드 일경우 max로 반환
		return sum == 0 ? Integer.MAX_VALUE : sum;
	}
	static class Node{
		int next, v;
		Node(int n, int v){
			this.next = n;
			this.v = v;
		}
	}
}

class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;

    int nextInt() throws Exception {
        int n = 0;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32) { if (size < 0) return -1; }
        if (c == 45) { c = read(); isMinus = true; }
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber(c = read()));
        return isMinus ? ~n + 1 : n;
    }

    boolean isNumber(byte c) {
        return 47 < c && c < 58;
    }

    byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}
