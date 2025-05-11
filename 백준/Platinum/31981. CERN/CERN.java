import java.io.*;
import java.util.*;

public class Main {
    static int N, B, Q;
    static int[] A = new int[505050];
    static int[] R = new int[505050];
    static int[] C = new int[505050];
    static int[] D = new int[505050];
    static int MX, CNT, CNT2;
    
    static class Qry implements Comparable<Qry> {
        int i, s, e;
        
        public Qry(int i, int s, int e) {
            this.i = i;
            this.s = s;
            this.e = e;
        }
        
        @Override
        public int compareTo(Qry a) {
            if (s / B == a.s / B) {
                return ((s / B) & 1) == 1 ? Integer.compare(a.e, e) : Integer.compare(e, a.e);
            }
            return Integer.compare(s, a.s);
        }
    }
    
    static void sub(int v) {
        if (C[v] == MX && D[C[v]] == 1) MX--;
        D[C[v]]--;
        D[C[v] - 1]++;
        C[v]--;
        if (C[v] == 0) CNT--;
        if (C[v] == 1) CNT2--;
    }
    
    static void add(int v) {
        if (C[v] == MX && D[C[v] + 1] == 0) MX++;
        D[C[v] + 1]++;
        D[C[v]]--;
        C[v]++;
        if (C[v] == 1) CNT++;
        if (C[v] == 2) CNT2++;
    }
    
    static int ans(int len, int mx, int cnt, int cnt2) {
        if (cnt == 2 && len == mx * 2) return 0;
        if (mx * 2 >= len) return 1;
        return (len & 1) == 1 ? cnt : cnt2;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        B = (int) Math.sqrt(N);
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        
        ArrayList<Qry> qry = new ArrayList<>();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            qry.add(new Qry(i, s, e));
        }
        
        Collections.sort(qry);
        D[0] = N;
        
        int s = qry.get(0).s;
        int e = qry.get(0).e;
        
        for (int i = s; i <= e; i++) {
            add(A[i]);
        }
        
        R[qry.get(0).i] = ans(qry.get(0).e - qry.get(0).s + 1, MX, CNT, CNT2);
        
        for (int i = 1; i < Q; i++) {
            while (s > qry.get(i).s) add(A[--s]);
            while (e < qry.get(i).e) add(A[++e]);
            while (s < qry.get(i).s) sub(A[s++]);
            while (e > qry.get(i).e) sub(A[e--]);
            
            R[qry.get(i).i] = ans(qry.get(i).e - qry.get(i).s + 1, MX, CNT, CNT2);
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            sb.append(R[i]).append("\n");
        }
        
        System.out.print(sb.toString());
    }
}