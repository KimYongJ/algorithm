import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String s = br.readLine();

        char[] room = new char[10];
        for (int i = 0; i < 10; i++) room[i] = '0';

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c == 'L') {
                for (int j = 0; j < 10; j++) {
                    if (room[j] == '0') {
                        room[j] = '1';
                        break;
                    }
                }
            } else if (c == 'R') {
                for (int j = 9; j >= 0; j--) {
                    if (room[j] == '0') {
                        room[j] = '1';
                        break;
                    }
                }
            } else {
                room[c - '0'] = '0';
            }
        }

        System.out.println(new String(room));
    }
}