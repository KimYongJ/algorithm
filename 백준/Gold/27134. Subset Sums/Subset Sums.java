import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 최대 합 계산
        int sum = N * (N + 1) / 2;
        
        // 합이 홀수이면 두 부분 집합으로 나눌 수 없음
        if (sum % 2 != 0) {
            System.out.println(0);
            return;
        }
        
        // target은 두 부분 집합의 합이 동일하도록 나누기 위한 목표 값
        int target = sum / 2;
        
        // dp[i]는 합이 i가 되는 경우의 수
        long[] dp = new long[target + 1];
        dp[0] = 1; // 합이 0인 경우는 1가지 (빈 집합)
        
        // 1부터 N까지의 숫자를 차례로 처리
        for (int i = 1; i <= N; i++) {
            for (int j = target; j >= i; j--) {
                dp[j] += dp[j - i];
            }
        }

        // dp[target]은 target 합을 만들 수 있는 경우의 수
        // 두 부분 집합이 동일한 합을 갖는 경우는 중복된 경우가 있기 때문에 / 2로 나눔
        System.out.println(dp[target] / 2);
    }
}
