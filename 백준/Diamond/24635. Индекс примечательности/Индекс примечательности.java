//https://www.acmicpc.net/problem/24635
//2초 512MB
//13// 소수 P(2≤P≤ 10^8 + 7)
//070070// 숫자 문자열T( 1≤|T|≤10^5)
//3//쿼리 수q (1≤q≤10^5)
//1 6// 구간
//2 5
//2 2
//문자열 S의 서로 다른 위치 쌍 i,j(i<=j) 중, S의 i번째 부터 j번째까지 연속된 숫자로 이뤄진 수가 P로 나누어지는 경우의 수를 출력(맨 앞에 0이면 무시함)
//9
//4
//0
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static String S;// 숫자 문자열T( 1≤|T|≤10^5)
    static long P;// 소수 P(2≤P≤ 10^8 + 7)
    static int N, Q;
    static long ans;// 현재 윈도우 내 “흥미 쌍” 개수
    static int[] cnt;// 빈도 배열 (좌표 압축 인덱스 → 등장 횟수)
    static List<Query> query;
    
    public static void main(String[] args) throws Exception {
        P = Integer.parseInt(br.readLine());// 소수 P
        S = br.readLine();// 숫자 문자열
        N = S.length();
        Q = Integer.parseInt(br.readLine());// 질의 수
        query = new ArrayList<>();
        
        // 소수가 2 또는 5인 경우 별도 연산
        if (P == 2 || P == 5)
        {
        	cal();
        	return;
        }

        // 소수가 2, 5가 아닌경우 이하 연산
        
        long[] suffixMod = new long[N + 2];      // A[i] : i부터 끝까지 mod P
        long[] pow10     = new long[N + 2];      // 10^k mod P (역산용)
        pow10[0] = 1;
        for (int i = 1; i <= N + 1; i++)
            pow10[i] = (pow10[i - 1] * 10) % P;

        // 뒤에서부터 suffix 값 계산, 동시에 좌표 압축용 리스트에 저장
        long[] rawVals = new long[N + 2];
        int rawCnt = 0;
        long b = 1;
        
        for (int i = N; i >= 1; i--)
        {
            int digit = S.charAt(i - 1) - '0';
            suffixMod[i] = (suffixMod[i + 1] + digit * b) % P;
            b = (b * 10) % P;
            rawVals[rawCnt++] = suffixMod[i];
        }
        
        rawVals[rawCnt++] = 0; // A[N+1] == 0 도 반드시 포함

        // 좌표 압축
        long[] uniq = Arrays.copyOf(rawVals, rawCnt);
        
        Arrays.sort(uniq);
        
        int m = 1;
        
        for (int i = 1; i < uniq.length; i++)
        	if (uniq[i] != uniq[m - 1])
        		uniq[m++] = uniq[i];
        
        uniq = Arrays.copyOf(uniq, m);

        int[] val = new int[N + 2]; // 압축된 값
        
        for (int i = 1; i <= N + 1; i++)
            val[i] = Arrays.binarySearch(uniq, suffixMod[i]);
        
        cnt = new int[m + 1];

        for (int qi = 0, blockSize = (int)Math.sqrt(N); qi < Q; qi++)
        {
        	st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            query.add(new Query(qi, l, r, l / blockSize));
        }
        
        Collections.sort(query);

        long[] answer = new long[Q];

        ans = 0;
        int curL = 1;
        int curR = 0;
        // 나머지 질의
        for(Query q : query)
        {
            while (curL > q.l) add(val[--curL]);
            while (curR < q.r) add(val[++curR]);
            while (curL < q.l) remove(val[curL++]);
            while (curR > q.r) remove(val[curR--]);

            answer[q.idx] = ans + cnt[val[q.r + 1]];
        }

        // 출력
        StringBuilder out = new StringBuilder();
        
        for (long res : answer)
        	out.append(res).append('\n');
        
        System.out.print(out);
    }
    private static void add(int idx) {        // 원소 추가
        ans += cnt[idx];
        cnt[idx]++;
    }
    private static void remove(int idx) {     // 원소 제거
        cnt[idx]--;
        ans -= cnt[idx];
    }
    static void cal() throws Exception
    {
    	// 2또는 5의 배수인 모든 경우의 수 찾기 
    	
        long[] prefSumPos = new long[N + 1];// 위치를 누적
        int[] prefCnt = new int [N + 1];// 끝자리가 2또는 5의 배수인 칸이 몇개인가 누적해나감

        for (int i = 1; i <= N; i++)
        {
            prefSumPos[i] = prefSumPos[i - 1];
            prefCnt[i] = prefCnt[i - 1];
            
            int digit = S.charAt(i - 1) - '0';
            
            if (digit % P == 0)// 현재칸이 배수자리이면
            {
                prefSumPos[i] += i;
                prefCnt[i]++;
            }
        }

        StringBuilder out = new StringBuilder();
        
        while(Q-->0)
        {
        	st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            
            long sumJ = prefSumPos[r] - prefSumPos[l - 1];
            long cntJ = (long)(prefCnt[r] - prefCnt[l - 1]) * (l - 1);
            // 경우의 수 출력
            out.append( sumJ - cntJ ).append('\n');
        }
        System.out.print(out);
    }
    
    static final class Query implements Comparable<Query> {
        int idx, l, r, fac;

        Query(int idx, int l, int r, int fac) {
            this.idx = idx;
            this.l = l;
            this.r = r;
            this.fac = fac;
        }

        @Override
        public int compareTo(Query o) {
        	if(fac != o.fac)
        		return fac - o.fac;
        	
        	if((fac & 1) == 0)
        		return r - o.r;
        	
        	return o.r - r;
        }
    }
}
