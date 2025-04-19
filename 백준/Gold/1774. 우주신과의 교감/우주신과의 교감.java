import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 두 정점(a, b)을 연결하는 간선을 표현하는 클래스
// Kruskal 알고리즘을 위해 거리(dist) 기준으로 오름차순 정렬 가능하도록 Comparable 구현
class Node implements Comparable<Node> {
    int a, b;        // 연결된 두 노드 번호
    double dist;     // 두 노드 사이 거리

    Node(int a, int b, double dist) {
        this.a = a;
        this.b = b;
        this.dist = dist;
    }

    @Override
    public int compareTo(Node o) {
        return Double.compare(this.dist, o.dist);
    }
}

public class Main {
    // Union–Find: 특정 노드의 최상위 부모를 찾는 메서드 (경로 압축 포함)
    public static int getParent(int node, int[] parent) {
        if (parent[node] == node) {
            return node;
        }
        // 재귀 호출로 부모를 갱신해 두면 이후 호출이 빠름 (경로 압축)
        return parent[node] = getParent(parent[node], parent);
    }

    public static void main(String[] args) throws Exception {
        // 1) 입력 및 자료구조 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 우주신의 개수 (1 ≤ N ≤ 1000)
        int M = Integer.parseInt(st.nextToken()); // 이미 연결된 터널 개수 (0 ≤ M ≤ 1000)
        
        // parent[i]: Union–Find 용 부모 정보 저장
        int[] parent = new int[N + 1];
        // data[i][0], data[i][1]: i번째 우주신의 x, y 좌표
        int[][] data = new int[N + 1][2];
        
        // 2) 각 우주신의 좌표 입력 및 parent 초기화
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            data[i][0] = Integer.parseInt(st.nextToken());
            data[i][1] = Integer.parseInt(st.nextToken());
            parent[i] = i;  // 처음에는 자기 자신을 부모로 설정
        }
        
        // 3) 이미 연결된 M개의 터널 정보를 Union 처리
        //    이렇게 하면 MST 시작 전부터 이들 우주신이 같은 집합으로 묶임
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            
            int pu = getParent(u, parent);
            int pv = getParent(v, parent);
            if (pu != pv) {
                // 작은 대표 노드 번호 쪽으로 합친다
                if (pu < pv) parent[pv] = pu;
                else         parent[pu] = pv;
            }
        }
        
        // 4) 모든 우주신 쌍에 대해 간선 생성하여 우선순위 큐에 삽입
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                double dx = data[i][0] - data[j][0];
                double dy = data[i][1] - data[j][1];
                double d = Math.sqrt(dx * dx + dy * dy);
                pq.add(new Node(i, j, d));
            }
        }
        
        // 5) Kruskal 알고리즘으로 최소 추가 비용 계산
        double ans = 0;
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int pa = getParent(now.a, parent);
            int pb = getParent(now.b, parent);
            
            // 서로 다른 대표를 갖고 있으면 연결해도 사이클이 생기지 않음
            if (pa != pb) {
                if (pa < pb) parent[pb] = pa;
                else          parent[pa] = pb;
                ans += now.dist;  // 이 간선을 추가한 비용 누적
            }
        }
        
        // 6) 결과 출력: 소수점 둘째 자리까지
        //    printf의 "%.2f"가 반올림하여 출력해 줌
        System.out.printf("%.2f\n", ans);
    }
}
