//https://www.acmicpc.net/problem/16130
//0.1 / 32MB
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            while (s != null && s.isEmpty()) s = br.readLine(); // 혹시 모를 빈 줄 방어
            if (s == null) s = "";
            s = s.trim();

            int point = 0;
            int weeks = 0;
            String suffix = "";

            for (int idx = 0; idx < s.length(); idx++) {
                char c = s.charAt(idx);

                int value;
                if ('0' <= c && c <= '9') value = c - '0';
                else value = c - 'A' + 10; // 'A'~'Z' => 10~35

                int next = point + value;

                if (next >= 50) {          // 몫 5 이상 => 영구 퇴사
                    suffix = "(09)";
                    break;
                }
                if (next >= 40) {          // 몫 4 => 무기 퇴사
                    suffix = "(weapon)";
                    break;
                }

                int oldQ = point / 10;
                int newQ = next / 10;
                if (newQ > oldQ) {
                    weeks += newQ;         // 증가한 "새 몫"만큼 주수 누적
                }

                point = next;
            }

            out.append(weeks).append(suffix).append('\n');
        }

        System.out.print(out);
    }
}