import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        for (int tc = 1; tc <= T; tc++) {
            int S = Integer.parseInt(br.readLine()); // 두 주사위 눈의 합

            sb.append("Case ").append(tc).append(":\n");

            // a ≤ b, 1~6 사이의 눈만 고려
            for (int a = 1; a <= 6; a++) {
                for (int b = a; b <= 6; b++) {
                    if (a + b == S) {
                        sb.append("(").append(a).append(",").append(b).append(")\n");
                    }
                }
            }
        }

        System.out.print(sb.toString());
    }
}