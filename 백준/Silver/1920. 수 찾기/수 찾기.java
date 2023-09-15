import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        HashSet<Integer> hs = new HashSet<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            hs.add(Integer.parseInt(st.nextToken()));
        }
        
        int m = Integer.parseInt(br.readLine());
        
        st = new StringTokenizer(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<m; i++){
            int x = Integer.parseInt(st.nextToken());
            if(hs.contains(x)){
                sb.append("1").append("\n");
            }else{
                sb.append("0").append("\n");
            }
        }
        
        System.out.println(sb.toString());
    }
}