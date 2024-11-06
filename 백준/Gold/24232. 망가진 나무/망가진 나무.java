import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE; // 정수 최대값을 사용하여 무한대를 표현합니다.

    static class Node {
        int vertex; // 연결된 정점
        boolean flag; // 엣지의 방향성 플래그
        int idx; // 엣지의 인덱스

        public Node(int vertex, boolean flag, int idx) {
            this.vertex = vertex;
            this.flag = flag;
            this.idx = idx;
        }
    }

    static int N; // 정점의 개수
    static List<List<Node>> edge = new ArrayList<>(); // 각 정점에서 연결된 엣지 리스트
    static boolean[] flip; // 각 엣지를 뒤집어야 하는지 여부
    static boolean[] ans; // 결과를 저장하는 배열
    static int minFlip = INF; // 최소 뒤집기 횟수

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        flip = new boolean[N - 1];
        ans = new boolean[N - 1];
        for (int i = 0; i <= N; i++) {
            edge.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            edge.get(u).add(new Node(v, false, i)); // u에서 v로 가는 엣지, 초기 방향은 false
            edge.get(v).add(new Node(u, true, i)); // v에서 u로 가는 엣지, 반대 방향은 true
        }

        int flipCount = init(1, 0); // 초기화 함수를 호출하여 뒤집힌 엣지 수를 계산
        dfs(1, 0, flipCount); // 깊이 우선 탐색을 시작

        for (int i = 0; i < N - 1; i++) {
            System.out.print(ans[i] ? 1 : 0); // 결과 출력
        }
        sc.close();
    }

    static int init(int node, int prev) {
        int sum = 0;
        for (Node n : edge.get(node)) {
            if (n.vertex == prev) continue; // 이전 정점을 다시 방문하지 않도록 예외 처리
            flip[n.idx] = n.flag; // 엣지의 초기 방향 설정
            sum += init(n.vertex, node) + (n.flag ? 1 : 0); // 재귀적으로 탐색하면서 뒤집힌 엣지 수를 누적
        }
        return sum;
    }

    static void dfs(int node, int prev, int flipCount) {
        if (minFlip > flipCount) { // 현재 뒤집기 횟수가 이전 최소값보다 작은 경우 갱신
            minFlip = flipCount;
            for (int i = 0; i < N - 1; i++) {
                ans[i] = flip[i]; // 결과 배열을 현재 상태로 업데이트
            }
        }
        for (Node n : edge.get(node)) {
            if (n.vertex == prev) continue; // 이전 정점 방문 방지
            boolean currentFlag = flip[n.idx];
            flip[n.idx] = !currentFlag; // 현재 엣지의 방향을 뒤집음
            dfs(n.vertex, node, flipCount + (flip[n.idx] ? 1 : -1)); // 재귀적으로 깊이 우선 탐색
            flip[n.idx] = currentFlag; // 방향을 원래대로 복구
        }
    }
}