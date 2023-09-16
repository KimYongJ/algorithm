import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashMap<String,Integer> hm = new HashMap<>();
        for(int i=0; i<n; i++){
            String str = st.nextToken();
            hm.put(str,hm.getOrDefault(str,0)+1);
        }
        
        StringBuilder sb = new StringBuilder();
        
        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++){
            String str = st.nextToken();
            sb.append(hm.getOrDefault(str,0)).append(" ");
        }
        System.out.println(sb.toString());
        
    }
}