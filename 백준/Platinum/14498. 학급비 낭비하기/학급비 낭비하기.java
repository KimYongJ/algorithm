import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    static List<Integer>[] g;
    static int[][] arr;
    static int[] match;
    
    static int time;
    static int[] visitTime;
    
    static int match(int v) {
        for(int i : g[v]) {
            if(visitTime[i] == time)
            	continue;
            
            visitTime[i] = time;
            
            if(match[i] == -1 || match(match[i]) == 1) {
            	match[i] = v;
                return 1;
            }
        }
        return 0;
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        g = new ArrayList[k + 1];
        arr = new int[600][3];
        match = new int[k + 1];
        visitTime = new int[k + 1];
        
        // Initialize adjacency list
        for(int i = 0; i <= k; i++) {
            g[i] = new ArrayList<>();
        }
        
        Arrays.fill(match, -1);
        
        for(int i = 0; i < k; i++)
        {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
        }
        
        // Make graph
        for(int i = 0; i < k; i++)
        {
            for(int j = i + 1; j < k; j++)
            {
                if((arr[i][0] == arr[j][0] || arr[i][1] == arr[j][1]) && arr[i][2] != arr[j][2])
                {
                    if(arr[i][2] == 1)
                        g[j].add(i);
                    else
                        g[i].add(j);
                }
            }
        }
        
        int ans = 0;
        for(int i = 0; i < k; i++)
        {
            ++time;
            if(match(i) == 1)
                ans++;
        }
        
        System.out.println(ans);
    }
}