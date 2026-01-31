//https://www.acmicpc.net/problem/13216
//1초 64MB
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력은 A/B로만 이루어진 시퀀스 1개. (개행/공백 섞여도 안전하게 처리)
        StringBuilder seq = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (c == 'A' || c == 'B') seq.append(c);
            }
        }

        int scoreA = 0, scoreB = 0;
        int gameA = 0, gameB = 0;

        StringBuilder out = new StringBuilder();
        char winner = '?';

        for (int i = 0; i < seq.length(); i++) {
            char p = seq.charAt(i);

            if (p == 'A') scoreA++;
            else scoreB++;

            // 게임 종료
            if (scoreA == 21 || scoreB == 21) {
                out.append(scoreA).append('-').append(scoreB).append('\n');

                if (scoreA == 21) gameA++;
                else gameB++;

                // 경기 종료(2선승)면 승자 출력하고 끝
                if (gameA == 2 || gameB == 2) {
                    winner = p; // 마지막 득점자 = 마지막 게임 승자 = 매치 승자
                    break;
                }

                // 다음 게임으로 리셋
                scoreA = 0;
                scoreB = 0;
            }
        }

        out.append(winner).append('\n');
        System.out.print(out.toString());
    }
}
