import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static long[] prefix;      // 정가 누적합
    static long[] prefixHalf;  // 반값 누적합

    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 선물 개수 (1 <= 100,000)
        long M = Long.parseLong(st.nextToken());  // 예산 (1 <= 1e9)
        int G = Integer.parseInt(st.nextToken()); // 할인 가능 개수 (0 <= N)

        long[] arr = new long[N+1];
        
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        
        // 1) 오름차순 정렬
        Arrays.sort(arr, 1, N+1);
        
        // 2) prefix, prefixHalf 만들기
        prefix     = new long[N+1];
        prefixHalf = new long[N+1];
        for(int i = 1; i <= N; i++) {
            prefix[i]     = prefix[i-1] + arr[i];
            prefixHalf[i] = prefixHalf[i-1] + (arr[i] / 2);
        }
        
        // 3) 이분 탐색으로 "살 수 있는 최대 개수" 찾기
        int left = 0, right = N;
        int answer = 0;
        while(left <= right) {
            int mid = (left + right) / 2;  // mid개를 살 수 있는지?

            long cost = getCost(mid, G);   // mid개 구매할 때의 최소 비용
            if(cost <= M) {
                // mid개 살 수 있다 => 더 많이 살 수 있는지 찾기
                answer = mid;
                left = mid + 1;
            } else {
                // 못 산다 => 개수 줄이기
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }

    // x개를 구매할 때 소요되는 최소 비용(가장 비싼 G개를 할인)
    static long getCost(int x, int G) {
        if(x == 0) return 0;     // 아무것도 안 사면 0원
        if(x <= G) {
            // x개 전부 반값 가능
            return prefixHalf[x];
        } else {
            // 앞 x-G개는 정가, 뒤 G개는 반값
            return prefix[x - G] + (prefixHalf[x] - prefixHalf[x - G]);
        }
    }
}
