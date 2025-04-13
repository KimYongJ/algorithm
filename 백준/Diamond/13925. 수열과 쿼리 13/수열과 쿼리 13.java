import java.io.*;
import java.util.*;

public class Main {
    // MOD 연산 상수
    static final long MOD = 1000000007L;
    // 배열의 최대 크기 (문제에서는 N이 최대 100,000 이므로, 4배 크기로 선언)
    static int N, M;
    static long[] segTree;    // 세그먼트 트리
    static Lazy[] lazy;       // lazy 배열 (각 노드는 (mul, add) 연산 정보를 가짐)

    // Lazy 클래스: 각 노드의 lazy 정보를 저장 (초깃값은 곱하기 1, 더하기 0)
    static class Lazy {
        long mul, add;
        public Lazy(long mul, long add) {
            this.mul = mul;
            this.add = add;
        }
    }

    // lazy 값 적용: 현재 노드(idx)의 lazy 정보를 하위 노드에 전파하고, 현재 노드의 값을 업데이트 한다.
    static void setLazy(int s, int e, int idx) {
        // 현재 lazy 정보를 임시로 저장
        Lazy tmp = lazy[idx];
        // 현재 노드의 lazy 값 초기화 (초깃값: 곱하기 1, 더하기 0)
        lazy[idx] = new Lazy(1, 0);

        // 세그먼트 트리 노드 업데이트
        segTree[idx] = (segTree[idx] * tmp.mul) % MOD;
        segTree[idx] = (segTree[idx] + (e - s + 1) * tmp.add) % MOD;

        // 리프 노드가 아니라면 하위 노드에 lazy 전파
        if (s != e) {
            // 왼쪽 자식
            lazy[idx * 2].mul = (lazy[idx * 2].mul * tmp.mul) % MOD;
            lazy[idx * 2].add = (lazy[idx * 2].add * tmp.mul + tmp.add) % MOD;
            // 오른쪽 자식
            lazy[idx * 2 + 1].mul = (lazy[idx * 2 + 1].mul * tmp.mul) % MOD;
            lazy[idx * 2 + 1].add = (lazy[idx * 2 + 1].add * tmp.mul + tmp.add) % MOD;
        }
    }

    /**
     * 구간 업데이트 함수
     * @param s 현재 구간의 시작 인덱스
     * @param e 현재 구간의 끝 인덱스
     * @param l 업데이트 구간의 시작 인덱스
     * @param r 업데이트 구간의 끝 인덱스
     * @param idx 세그먼트 트리 상의 현재 노드 번호
     * @param val 업데이트에 사용될 값
     * @param c 업데이트 유형 (1: 덧셈, 2: 곱셈, 3: 대입)
     */
    static void update(int s, int e, int l, int r, int idx, long val, int c) {
        // lazy 정보가 초기값이 아니라면 전파
        if (lazy[idx].mul != 1 || lazy[idx].add != 0) {
            setLazy(s, e, idx);
        }

        // 현재 구간과 업데이트 범위가 겹치지 않으면 리턴
        if (e < l || r < s) return;

        // 현재 구간이 업데이트 구간에 완전히 포함되는 경우
        if (l <= s && e <= r) {
            if (c == 1) {  // 덧셈 업데이트
                segTree[idx] = (segTree[idx] + (e - s + 1) * val) % MOD;
                if (s != e) {
                    lazy[idx * 2].add = (lazy[idx * 2].add + val) % MOD;
                    lazy[idx * 2 + 1].add = (lazy[idx * 2 + 1].add + val) % MOD;
                }
            } else if (c == 2) {  // 곱셈 업데이트
                segTree[idx] = (segTree[idx] * val) % MOD;
                if (s != e) {
                    lazy[idx * 2].mul = (lazy[idx * 2].mul * val) % MOD;
                    lazy[idx * 2].add = (lazy[idx * 2].add * val) % MOD;
                    lazy[idx * 2 + 1].mul = (lazy[idx * 2 + 1].mul * val) % MOD;
                    lazy[idx * 2 + 1].add = (lazy[idx * 2 + 1].add * val) % MOD;
                }
            } else if (c == 3) {  // 대입(치환) 업데이트
                segTree[idx] = ((e - s + 1) * val) % MOD;
                if (s != e) {
                    // 대입은 lazy 정보를 완전히 덮어씌움 (곱셈 0, 더하기 val)
                    lazy[idx * 2] = new Lazy(0, val);
                    lazy[idx * 2 + 1] = new Lazy(0, val);
                }
            }
            return;
        }

        // 분할 정복: 좌측과 우측 구간에 재귀 호출
        int mid = (s + e) / 2;
        update(s, mid, l, r, idx * 2, val, c);
        update(mid + 1, e, l, r, idx * 2 + 1, val, c);
        // 현재 노드 업데이트: 좌, 우측 값의 합을 MOD 연산 후 저장
        segTree[idx] = (segTree[idx * 2] + segTree[idx * 2 + 1]) % MOD;
    }

    /**
     * 구간 합 쿼리 함수
     * @param s 현재 구간의 시작 인덱스
     * @param e 현재 구간의 끝 인덱스
     * @param l 쿼리 구간의 시작 인덱스
     * @param r 쿼리 구간의 끝 인덱스
     * @param idx 세그먼트 트리 상의 현재 노드 번호
     * @return 구간 합 (MOD 적용)
     */
    static long getVal(int s, int e, int l, int r, int idx) {
        if (lazy[idx].mul != 1 || lazy[idx].add != 0) {
            setLazy(s, e, idx);
        }
        if (e < l || r < s) return 0;
        if (l <= s && e <= r) return segTree[idx];
        int mid = (s + e) / 2;
        return (getVal(s, mid, l, r, idx * 2) + getVal(mid + 1, e, l, r, idx * 2 + 1)) % MOD;
    }

    public static void main(String[] args) throws Exception {
        // 빠른 입출력을 위한 BufferedReader와 PrintWriter 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        // 첫 번째 입력: 배열 크기 N
        N = Integer.parseInt(br.readLine().trim());
        // 세그먼트 트리와 lazy 배열의 사이즈는 4*(N+1)로 선언 (1-indexed 사용)
        int size = 4 * (N + 1);
        segTree = new long[size];
        lazy = new Lazy[size];
        // lazy 배열 초기화: 각 노드가 (1, 0)으로 설정됨
        for (int i = 0; i < size; i++) {
            lazy[i] = new Lazy(1, 0);
        }

        // N개의 원소 초기화
        // 입력이 한 줄 또는 여러 줄에 걸쳐 들어올 수 있으므로 StringTokenizer를 활용함
        StringTokenizer st = new StringTokenizer(br.readLine());
        int count = 0;
        while (count < N) {
            if (!st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }
            long ai = Long.parseLong(st.nextToken());
            count++;
            // 대입 업데이트(c == 3)를 이용해서 i번째 원소를 ai로 설정
            update(1, N, count, count, 1, ai, 3);
        }

        // 두 번째 입력: 쿼리의 개수 M
        M = Integer.parseInt(br.readLine().trim());
        // 각 쿼리를 처리
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            if (c == 4) {  // 구간 합 쿼리
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                out.println(getVal(1, N, x, y, 1));
            } else {
                // c = 1, 2, 3 : 구간 업데이트 쿼리
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                long v = Long.parseLong(st.nextToken());
                update(1, N, x, y, 1, v, c);
            }
        }
        out.flush();
        out.close();
    }
}
