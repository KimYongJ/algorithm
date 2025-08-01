//https://www.acmicpc.net/problem/24955
//1초 512MB
//3 2 // 집의 수(2≤N≤1,000), 놀이 횟수 (1≤Q≤1,000)
//10 9 1// N개의 대문에 쓰여있는 수가 공백으로 주어짐(1≤Ai≤1,000,000,000(1≤i≤N))
//1 2 // N-1개의 도로 정보가 주어짐(1≤ai,bi≤N)
//2 3
//1 3 // 놀이횟수만큼 출발노드, 도착노드가 각각 주어진다. (1≤xi,yi≤N)
//3 1
//첫 번째 줄부터Q번째 줄에 걸쳐,i번째 줄에는i번째 놀이의 결과를1,000,000,007로 나눈 나머지를 출력한다.
//1091
//1910
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static final long mod = 1_000_000_007;
	static int N, Q;
	static long num[];
	static int idx;
	static int chainIdx[];
	static int chainLevel[];
	static int chainParent[];
	static int chainHeader[];
	static List<Integer> adList[];
	static List<Long> forward, reverse;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 집의 수(2≤N≤1,000)
		Q = Integer.parseInt(st.nextToken());// 놀이 횟수 (1≤Q≤1,000)
		num = new long[N + 1];
		chainIdx = new int[N + 1];
		chainLevel = new int[N + 1];
		chainParent = new int[N + 1];
		chainHeader = new int[N + 1];
		adList = new ArrayList[N + 1];
		chainHeader[1] = 1;
		chainParent[1] = 1;
		chainLevel[1] = 1;
		forward = new ArrayList<>();
		reverse = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
		{
			num[i] = Integer.parseInt(st.nextToken());
			adList[i] = new ArrayList<>();
		}
		
		for(int i=1; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adList[a].add(b);
			adList[b].add(a);
		}
		
		setChild(1, 0, new int[N + 1]);
		setHLD(1, 0, 1);
		
		StringBuilder sb = new StringBuilder();
		while(Q-->0)
		{
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			sb.append(getAns(s, e)).append('\n');
		}
		System.out.print(sb);
	}
	static long getAns(int s, int e)
	{
		forward.clear();
		reverse.clear();
		
		while(chainHeader[s] != chainHeader[e])
		{
			if(chainLevel[s] < chainLevel[e])
			{
				reverse.add(0,num[e]);
				e = chainParent[e];
				continue;
			}
			forward.add(num[s]);
			s = chainParent[s];
		}
		while(chainIdx[s] != chainIdx[e])
		{
			if(chainIdx[s] > chainIdx[e])
			{
				forward.add(num[s]);
				s = chainParent[s];
			}
			else
			{
				reverse.add(0,num[e]);
				e = chainParent[e];
			}
		}
		
		forward.add(num[s]);
		
		long res = 0;
		
		for(long now : forward)
		{
			res = (((res * getDigit(now)) % mod) + now) %mod;
		}
		for(long now : reverse)
		{
			res = (((res * getDigit(now)) % mod) + now) %mod;
		}
		
		return res;
	}
	static long getDigit(long now) {
		long mul = 1;
		while(now != 0)
		{
			mul = (mul * 10)%mod;
			
			now /= 10;
		}
		return mul;
	}
	static void setHLD(int now, int parent, int level) {
		chainIdx[now] = ++idx;
		chainLevel[now] = level;
		
		for(int i=0; i<adList[now].size(); i++)
		{
			int next = adList[now].get(i);
			if(next == parent)
				continue;
			
			chainParent[next] = now;// 자기 이전 노드로 점프하기 위해 이전 노드 마킹
			if(i == 0)// heavy child인 경우 체인 유지
			{
				chainHeader[next] = chainHeader[now];
				setHLD(next, now, level);
				continue;
			}
			// 가벼운 자식노드는 새로운 체인 시작
			chainHeader[next] = next;// 체인의 머리는 자기 자신
			setHLD(next, now, level + 1);// 레벨 추가
		}
	}
	static void setChild(int now, int parent, int size[]) {
		size[now] = 1;
		int heavyIdx = 0;
		int heavySize = 0;
		for(int i=0; i<adList[now].size(); i++)
		{
			int next = adList[now].get(i);
			if(next == parent)
				continue;
			
			setChild(next, now, size);
			
			size[now] += size[next];
			
			if(heavySize < size[next])
			{
				heavySize = size[next];
				heavyIdx = i;
			}
		}
		Collections.swap(adList[now], 0, heavyIdx);
	}
}