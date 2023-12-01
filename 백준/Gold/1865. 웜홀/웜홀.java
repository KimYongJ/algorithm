class Main {

    static final byte[] YES = new byte[]{'Y', 'E', 'S', '\n'};
    static final byte[] NO = new byte[]{'N', 'O', '\n'};

    public static void main(String[] args) throws Exception {
        for (int i = read(); i > 0; i--) System.out.write(testcase());
        System.out.close();
    }

    static int N, M, W;
    static Node[] graph;
    static int[] times = new int[501];
    static final int INF = 1 << 29;

    static class Node {
        int e;
        int t;
        Node n;
        Node(int e, int t, Node n) {
            this.e = e;
            this.t = t;
            this.n = n;
        }
    }

    static byte[] testcase() throws Exception {

        N = read();
        M = read();
        W = read();

        graph = new Node[N + 1];
        for (int i = 0; i < M; i++) {
            int s = read();
            int e = read();
            int t = read();
            graph[s] = new Node(e, t, graph[s]);
            graph[e] = new Node(s, t, graph[e]);
        }
        for (int i = 0; i < W; i++) {
            int s = read();
            int e = read();
            int t = read();
            graph[s] = new Node(e, -t, graph[s]);
        }

        return bellmanFord() ? YES : NO;

    }

    static boolean bellmanFord() {

        for (int i = 1; i <= N; i++) times[i] = INF;

        for (int i = 1; i <= N; i++) {
            boolean isUpdated = false;
            for (int s = 1; s <= N; s++) {
                for (Node node = graph[s]; node != null; node = node.n) {
                    int e = node.e;
                    int t = node.t + times[s];
                    if (times[e] > t) {
                        times[e] = t;
                        isUpdated = true;
                        if (i == N) return true;
                    }
                }
            }
            if (!isUpdated) break;
        }

        return false;

    }

    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

}