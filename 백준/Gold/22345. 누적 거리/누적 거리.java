import java.io.*;
import java.util.*;

class Main {
    static class Town { // 마을 정보를 저장하는 클래스
        long x, a;
        public Town(long x, long a) {
            this.x = x;
            this.a = a;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        // 입력: N = 마을의 수, Q = 질의의 수
        String[] firstLine = br.readLine().split(" ");
        int N = Integer.parseInt(firstLine[0]);
        int Q = Integer.parseInt(firstLine[1]);
        
        Town[] towns = new Town[N + 1]; // 마을 정보를 저장할 배열 (1-based indexing)
        long[] L = new long[N + 2]; // 왼쪽 누적 비용
        long[] R = new long[N + 2]; // 오른쪽 누적 비용
        long[] S = new long[N + 1]; // 인구 누적합
        long[] X = new long[N + 1]; // 위치 정보
        
        // 마을 데이터 입력
        for (int i = 1; i <= N; i++) {
            String[] input = br.readLine().split(" ");
            long a = Long.parseLong(input[0]);
            long x = Long.parseLong(input[1]);
            towns[i] = new Town(x, a);
        }
        
        // 마을 위치에 따라 정렬
        Arrays.sort(towns, 1, N + 1, Comparator.comparingLong(t -> t.x));
        
        // 누적합 계산
        for (int i = 1; i <= N; i++) {
            X[i] = towns[i].x;
            S[i] = S[i - 1] + towns[i].a;
            L[i] = L[i - 1] + towns[i].a * towns[i].x;
            int t = N - i + 1;
            R[t] = R[t + 1] + towns[t].a * towns[t].x;
        }
        
        // 질의 처리
        for (int i = 0; i < Q; i++) {
            long q = Long.parseLong(br.readLine().trim());
            int p = Arrays.binarySearch(X, 1, N + 1, q);
            if (p < 0) p = -p - 1; // 이진 탐색 실패 시 삽입 위치를 찾음
            
            // 결과 계산: q 위치에서 각 마을까지의 거리 비용 계산
            long result = q * S[p - 1] - L[p - 1] + R[p] - q * (S[N] - S[p - 1]);
            bw.write(result + "\n");
        }
        
        bw.flush();
        bw.close();
    }
}