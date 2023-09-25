import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.HashMap;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        HashMap<String,Integer> strHm = new HashMap<>();
        HashMap<Integer,String> intHm = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        for(int i=1; i<=n; i++){
            String str = br.readLine();
            strHm.put(str,i);
            intHm.put(i,str);
        }
        for(int i=0; i<m; i++){
            String str = br.readLine();
            
            if(isDigit(str)){
                sb.append(intHm.get(Integer.parseInt(str))).append("\n");
            }else{
                sb.append(strHm.get(str)).append("\n");
            }
        }
        System.out.println(sb.toString());
    }
    public static boolean isDigit(String str)throws Exception{
        try{
            Integer.parseInt(str);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}