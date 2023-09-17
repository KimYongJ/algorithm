import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++){
            String str = br.readLine();
            sb.append(excute(str)).append("\n");
        }
        System.out.println(sb.toString());
    }
    public static String excute(String str){
        int cnt = 0;
        for(char c : str.toCharArray()){
            if(c=='('){
                cnt++;
            }else{
                cnt--;
            }
            if(cnt<0){
                return "NO";
            }
        }
        return cnt != 0 ? "NO" : "YES";
    }
}