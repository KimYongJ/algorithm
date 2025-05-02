import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {
    static int sqrt;
    
    static class Query implements Comparable<Query> {
        int left, right, idx;
        Query(int l, int r, int i) {
            left = l;
            right = r;
            idx = i;
        }
        @Override
        public int compareTo(Query o) {
            int l = left / sqrt;
            int r = o.left / sqrt;
            return l == r ? Integer.compare(right, o.right) : Integer.compare(l, r);
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        int[] cnt = new int[100_001];
        int[] freq = new int[100_001]; // freq[i]: 빈도수가 i인 숫자의 개수
        sqrt = (int) Math.sqrt(N);
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int Q = Integer.parseInt(br.readLine());
        int[] ans = new int[Q + 1];
        ArrayList<Query> query = new ArrayList<>();
        
        for (int i = 1; i <= Q; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            query.add(new Query(l, r, i));
        }
        
        Collections.sort(query);
        
        int idxL = 1;
        int idxR = 0;
        int max = 0;
        
        for (Query q : query) {
            while (q.right < idxR) {
                int num = arr[idxR];
                freq[cnt[num]]--;
                if (cnt[num] == max && freq[cnt[num]] == 0) {
                    max--;
                }
                cnt[num]--;
                freq[cnt[num]]++;
                idxR--;
            }
            while (idxR < q.right) {
                idxR++;
                int num = arr[idxR];
                freq[cnt[num]]--;
                cnt[num]++;
                freq[cnt[num]]++;
                if (cnt[num] > max) {
                    max = cnt[num];
                }
            }
            while (idxL < q.left) {
                int num = arr[idxL];
                freq[cnt[num]]--;
                if (cnt[num] == max && freq[cnt[num]] == 0) {
                    max--;
                }
                cnt[num]--;
                freq[cnt[num]]++;
                idxL++;
            }
            while (q.left < idxL) {
                idxL--;
                int num = arr[idxL];
                freq[cnt[num]]--;
                cnt[num]++;
                freq[cnt[num]]++;
                if (cnt[num] > max) {
                    max = cnt[num];
                }
            }
            ans[q.idx] = max;
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= Q; i++) {
            sb.append(ans[i]).append('\n');
        }
        System.out.print(sb);
    }
}