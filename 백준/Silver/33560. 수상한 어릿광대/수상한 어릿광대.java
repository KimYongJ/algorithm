import java.util.*;
import java.io.*;

public class Main {
    static int score = 0, ppr = 1, tpt = 4, time = 0;
    static int[] rewards = new int[4];

    static void endGame() {
        if (score >= 125) rewards[3]++;
        else if (score >= 95) rewards[2]++;
        else if (score >= 65) rewards[1]++;
        else if (score >= 35) rewards[0]++;
        score = 0; ppr = 1; tpt = 4; time = 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            int d = Integer.parseInt(st.nextToken());

            if (time > 240) endGame();

            if (d == 1) { endGame(); continue; }
            else if (d == 2) { if (ppr > 1) ppr /= 2; else tpt += 2; }
            else if (d == 5) { if (tpt > 1) tpt--; }
            else if (d == 6) { if (ppr < 32) ppr *= 2; }

            score += ppr;
            time += tpt + (d == 4 ? 56 : 0);
        }

        StringBuilder sb = new StringBuilder();
        for (int r : rewards) sb.append(r).append('\n');
        System.out.print(sb);
    }
}