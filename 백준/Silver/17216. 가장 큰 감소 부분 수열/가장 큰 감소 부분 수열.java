import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.Arrays;

class Main {
    static int N;
    static int[] arr, dp, tree;
    static HashMap<Integer, Integer> map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        dp = new int[N + 1];
        tree = new int[4 * N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = arr[i];
        }

        // 좌표 압축
        int[] sortedArr = Arrays.copyOfRange(arr, 1, N + 1);
        Arrays.sort(sortedArr);
        map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            if (!map.containsKey(sortedArr[i])) {
                map.put(sortedArr[i], map.size() + 1);
            }
        }

        int max = 0;
        for (int i = 1; i <= N; i++) {
            int compressed = map.get(arr[i]);
            int prevMax = query(1, 1, N, compressed + 1, N);
            dp[i] = arr[i] + prevMax;
            update(1, 1, N, compressed, dp[i]);
            max = Math.max(max, dp[i]);
        }
        System.out.print(max);
    }

    public static int query(int node, int start, int end, int left, int right) {
        if (end < left || right < start) return 0;
        if (left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;
        int leftChild = query(node * 2, start, mid, left, right);
        int rightChild = query(node * 2 + 1, mid + 1, end, left, right);
        return Math.max(leftChild, rightChild);
    }

    public static void update(int node, int start, int end, int idx, int value) {
        if (start == end) {
            tree[node] = value;
            return;
        }

        int mid = (start + end) / 2;
        if (idx <= mid) {
            update(node * 2, start, mid, idx, value);
        } else {
            update(node * 2 + 1, mid + 1, end, idx, value);
        }
        tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
    }
}