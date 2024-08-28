// https://github.com/kimyongj/algorithm
// https://www.acmicpc.net/problem/4716
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Team implements Comparable<Team> {
        int count;
        int dist_a;
        int dist_b;

        Team(int count, int dist_a, int dist_b) {
            this.count = count;
            this.dist_a = dist_a;
            this.dist_b = dist_b;
        }

        @Override
        public int compareTo(Team o) { //내림차순
            return Integer.compare(Math.abs(o.dist_a - o.dist_b), Math.abs(this.dist_a - this.dist_b));
        }

        @Override
        public String toString() {
            return "Team{" +
                    "count=" + count +
                    ", dist_a=" + dist_a +
                    ", dist_b=" + dist_b +
                    '}';
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); //팀의 수
            int A = Integer.parseInt(st.nextToken()); //방A 풍선
            int B = Integer.parseInt(st.nextToken()); //방B 풍선

            if (N == 0 && A== 0 && B == 0) {
                break;
            }

            //각 팀의 정보
            ArrayList<Team> teams= new ArrayList<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int k = Integer.parseInt(st.nextToken()); //팀에게 달아줘야하는 풍선 수
                int dist_a = Integer.parseInt(st.nextToken()); //a와 떨어진 거리
                int dist_b = Integer.parseInt(st.nextToken()); //b와 떨어진 거리

                teams.add(new Team(k, dist_a, dist_b));
            }
            Collections.sort(teams); //a-b 거리가 가장 먼것부터 정렬 (내림차순)
            // 비슷하면 아무데서나 해도 상관이 없기 때문에 극단적인 거리를 가진 것부터 처리

            int result = 0;
            for (int i = 0; i < N; i++) {
                Team t = teams.get(i);

                if (t.dist_a < t.dist_b) { //방 a가 더 가까우면 a 풍선 사용
                    //t.count; //풍선의 개수
                    if (A >= t.count) { //방 a에 있는 풍선으로 다 달 수 있으면 a에서 다 사용
                        result += t.count * t.dist_a;
                        A -= t.count;
                    } else { //방 a 풍선으로 다 달지 못할 때 - 나머지는 b 사용하기
                        result += A * t.dist_a;
                        t.count -= A;
                        A = 0;

                        result += t.count * t.dist_b;
                        B -= t.count;
                    }

                } else { //방 b가 더 가까우면 b 풍선 사용

                    if (B >= t.count) { //방 a에 있는 풍선으로 다 달 수 있으면 a에서 다 사용
                        result += t.count * t.dist_b;
                        B -= t.count;
                    } else { //방 b 풍선으로 다 달지 못할 때 - 나머지는 a 사용하기
                        result += B * t.dist_b;
                        t.count -= B;
                        B = 0;

                        result += t.count * t.dist_a;
                        A-= t.count;
                    }
                }
            }

            System.out.println(result);
        }
    }
}