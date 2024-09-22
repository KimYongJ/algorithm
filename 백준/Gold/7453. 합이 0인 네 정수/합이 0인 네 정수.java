import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long cnt;
    static int[] A, B, C, D, AB, CD;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        A = new int[N];
        B = new int[N];
        C = new int[N];
        D = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);
        Arrays.sort(B);
        Arrays.sort(C);
        Arrays.sort(D);

        AB = new int[N * N];
        CD = new int[N * N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                AB[i * N + j] = A[i] + B[j];
                CD[i * N + j] = C[i] + D[j];
            }
        }
        
        Arrays.sort(AB);
        Arrays.sort(CD);

        int start = 0;
        int end = N * N - 1;

        while (start < N * N && end >= 0) {
            int sum = AB[start] + CD[end];

            if (sum == 0) {
                long cnt1 = 1;
                long cnt2 = 1;

                while (++start < N*N && AB[start] == AB[start-1]) {
                    cnt1++;
                }

                while(--end >= 0 && CD[end] == CD[end+1]) {
                    cnt2++;
                }

                cnt += cnt1 * cnt2;
            } else if (sum < 0) {
                start++;
            } else {
                end--;
            }
        }

        System.out.println(cnt);
    }
}