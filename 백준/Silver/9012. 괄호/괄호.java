import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++){
            String str = br.readLine();
            int cnt = 0;
            for(char c : str.toCharArray()){
                if(c=='('){
                    cnt++;
                }else{
                    cnt--;
                }
                if(cnt<0){
                    break;
                }
            }
            sb.append(cnt != 0 ? "NO" : "YES").append("\n");
        }
        System.out.println(sb.toString());
    }
}