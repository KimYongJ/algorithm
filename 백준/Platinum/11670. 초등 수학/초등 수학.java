//https://www.acmicpc.net/problem/11670
//10초 256MB
//4// 순서쌍 개수(1<=2,500)
//1 5// 순서쌍 A,B가 주어짐(|10^6|)
//3 3
//4 5
//-1 -6
//출력은 5개의 요소로 나뉨, A와 3개의 연산자(+,-,*)그리고 B,=,연산결과 이다. 모든 연산결과는 달라야 한다. 답이 없다면 impossible 출력
//1 + 5 = 6
//3 * 3 = 9
//4 - 5 = -1
//-1 - -6 = 5

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Main{

	static int N;
	static int result[];
	static long number[];
	static List<Query> query;
	
	// 이분 매칭시 사용
	static int time;
	static int match[];
	static int visitTime[];
	static List<Integer> adList[];// 인접리스트로, 각 질문당 갈 수 있는 숫자를 연결합니다.
	
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		N = in.nextInt();
		result = new int[N];
		number = new long[N * 3];
		query = new ArrayList<>();
		adList = new ArrayList[N + 1];
		
		for(int i=0; i<N; i++)
			adList[i] = new ArrayList<>();
		
		for(int i=0, j = 0; i<N; i++)
		{
			long a = in.nextInt();
			long b = in.nextInt();
			
			query.add(new Query(a,b));
			
			number[j++] = a + b;
			number[j++] = a - b;
			number[j++] = a * b;
		}
		
		// 값 압축을 위한 정렬
		Arrays.sort(number);
		// 값 압축을 통해 인접리스트 생성
		for(int i=0; i<N; i++)
		{
			Query now = query.get(i);
			// 이분 탐색으로 해당 인덱스의 위치 중 가장 작은 것을 가져옴
			adList[i].add(binarySearch(now.a + now.b));
			adList[i].add(binarySearch(now.a - now.b));
			adList[i].add(binarySearch(now.a * now.b));
		}
		
		match = new int[N * 3];
		visitTime = new int[N * 3];
		// 인덱스가 0 base 이므로 -1로 초기화
		Arrays.fill(match, -1);
		
		int cnt = 0;
		
		for(int i=0; i<N; i++)
		{
			++time;
			if(dfs(i))
				++cnt;
		}
		// 모두 매칭 불가시 impossible 출력
		if(cnt != N)
		{
			System.out.print("impossible");
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<N; i++)
		{
			Query q = query.get(i);// 질문을 
			// 연산 결과를 가져옴, 연산자는 값 압축응로 인해 안맞을 수 있어 직접 구해야함
			long num = number[result[i]];
			char op = '+';
			if(q.a - q.b == num)
				op = '-';
			if(q.a * q.b == num)
				op = '*';
			// 연산자를 구해 출력
			sb.append(q.a).append(' ').append(op).append(' ').append(q.b)
			.append(" = ").append(num).append('\n');
		}
		System.out.print(sb);
	}
	static boolean dfs(int i)
	{
		for(int j : adList[i])
		{
			if(visitTime[j] == time)
				continue;
			
			visitTime[j] = time;
			
			if(match[j] == -1 || dfs(match[j]))
			{
				match[j] = i;
				result[i] = j;
				return true;
			}
		}
		return false;
	}
	static int binarySearch(long target) {
		int s = 0;
		int e = number.length - 1;
		int res = 0;
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			if(number[mid] >= target)
			{
				res = mid;
				e = mid - 1;
			}
			else
				s = mid + 1;
		}
		return res;
	}
	static class Query{
		long a, b;
		Query(long a, long b){
			this.a=a;
			this.b=b;
		}
	}
	static class Reader {
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
}