import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        boolean[] check = new boolean[1001]; // 문제 조건상 점수는 1000 이하
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int x = Integer.parseInt(st.nextToken());
            check[x] = true;
        }

        int score = 0;
        for (int i = 0; i < N; i++) {
            score += A[i];
            if (check[score]) {
                score = 0;
            }
        }

        System.out.println(score);
    }
}