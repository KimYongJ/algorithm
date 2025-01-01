import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 학생 수
        int K = Integer.parseInt(st.nextToken()); // 졸고 있는 학생 수
        int Q = Integer.parseInt(st.nextToken()); // 출석 코드를 보낼 학생 수
        int M = Integer.parseInt(st.nextToken()); // 질의 수

        boolean[] sleep = new boolean[N + 3];
        int[] psum = new int[N + 3]; // 누적합 배열

        // 졸고 있는 학생 입력 처리
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int index = Integer.parseInt(st.nextToken());
            sleep[index] = true;
        }

        // 출석 코드를 보낼 학생 처리
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (sleep[num]) continue; // 졸고 있는 학생은 처리하지 않음

            for (int j = num; j < N + 3; j += num) {
                if (!sleep[j]) {
                    psum[j] = 1; // 출석 처리
                }
            }
        }

        // 누적합 계산
        for (int i = 3; i < N + 3; i++) {
            psum[i] += psum[i - 1];
        }

        // 질의 처리
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            sb.append((r - l + 1) - (psum[r] - psum[l - 1])).append('\n'); // 출석하지 않은 학생 수 계산
        }
        System.out.print(sb);
    }
}