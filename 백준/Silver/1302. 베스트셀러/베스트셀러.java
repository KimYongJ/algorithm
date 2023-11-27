// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
class Main{
    public static void main(String[] args)throws Exception{
        HashMap<String,Integer> hm = new HashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        
        int max = 0;
        String result = "z";
        
        for(int i=0; i<N; i++){
            String str = br.readLine();
            int cnt = hm.getOrDefault(str,0)+1;
            hm.put(str, cnt);
            if(max == cnt){
            	result = result.compareTo(str) < 0 ? result : str;
            }else if(max < cnt) {
            	max = cnt;
            	result = str;
            }
        }
        System.out.println(result);
    }
}