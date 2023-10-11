// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        
        while(T-->0){
            
            int n = Integer.parseInt(br.readLine());
            HashMap<String,Integer> hm = new HashMap<>();
            
            for(int i=0; i<n; i++){
                String[] str = br.readLine().split(" ");
                hm.put(str[1],hm.getOrDefault(str[1],0)+1);
            }
            
            int result = 1;
            
            for(int value : hm.values())
                result *= value+1;
            
            sb.append(result-1).append("\n");
        }
        System.out.println(sb);
    }
}