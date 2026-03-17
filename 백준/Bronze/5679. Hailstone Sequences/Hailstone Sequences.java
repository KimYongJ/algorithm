import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String line = br.readLine();
            if (line == null) break;

            line = line.trim();
            if (line.isEmpty()) continue;

            long h = Long.parseLong(line);
            if (h == 0) break;

            long cur = h;
            long maxVal = h;

            while (cur != 1) {
                if (cur % 2 == 0) {
                    cur /= 2;
                } else {
                    cur = cur * 3 + 1;
                }

                if (cur > maxVal) {
                    maxVal = cur;
                }
            }

            sb.append(maxVal).append('\n');
        }

        System.out.print(sb.toString());
    }
}