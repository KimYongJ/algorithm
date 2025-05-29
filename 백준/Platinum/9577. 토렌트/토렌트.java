import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    /* ====== 전역 변수 (C++ 원본과 동일한 이름 유지) ====== */
    static int part_num, seed_num;
    static int[] time_match;          // time  → part 매칭
    static int[] part_match;          // part  → time 매칭
    static boolean[] visited;         // DFS 방문 배열
    static boolean[][] adj;           // adj[time][part] : 해당 시간에 조각 다운로드 가능 여부

    /* --------------------------------------------------- */

    /** Kuhn-DFS : time(왼쪽)에서 시작해 증강 경로 탐색 */
    static boolean DFS(int time) {
        if (visited[time]) return false;
        visited[time] = true;

        for (int part = 1; part <= part_num; ++part) {
            if (adj[time][part]) {
                if (part_match[part] == -1 || DFS(part_match[part])) {
                    time_match[time] = part;
                    part_match[part] = time;
                    return true;
                }
            }
        }
        return false;
    }

    /* --------------------------------------------------- */

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int test_num = Integer.parseInt(br.readLine().trim());

        while (test_num-- > 0) {

            /* 입력 1 : 조각 수, 사용자 수 */
            st = new StringTokenizer(br.readLine());
            part_num = Integer.parseInt(st.nextToken());
            seed_num = Integer.parseInt(st.nextToken());

            /* 인접 행렬 초기화 (시각 0 ~ 100) */
            adj = new boolean[101][part_num + 1];

            int last_time = 0;

            /* 입력 2 : 사용자 정보 */
            for (int i = 0; i < seed_num; ++i) {
                st = new StringTokenizer(br.readLine());
                int start_time = Integer.parseInt(st.nextToken());
                int end_time   = Integer.parseInt(st.nextToken());
                int have_parts = Integer.parseInt(st.nextToken());

                last_time = Math.max(last_time, end_time);

                for (int j = 0; j < have_parts; ++j) {
                    int part_idx = Integer.parseInt(st.nextToken());

                    /* 원본 로직 그대로: [start_time, end_time) 구간에 간선 추가 */
                    for (int time = start_time; time < end_time; ++time) {
                        adj[time][part_idx] = true;
                    }
                }
            }

            /* 매칭 배열 초기화 */
            time_match = new int[last_time];
            for (int i = 0; i < last_time; i++) time_match[i] = -1;

            part_match = new int[part_num + 1];
            for (int i = 0; i <= part_num; i++) part_match[i] = -1;

            /* 증강 경로 수행 */
            int download_size = 0;
            int finished_time = -1;

            for (int start = 0; start < last_time; ++start) {

                visited = new boolean[last_time];

                if (DFS(start)) ++download_size;

                if (download_size == part_num) {
                    finished_time = start + 1;
                    break;
                }
            }

            System.out.println(finished_time);
        }
    }
}
