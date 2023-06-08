import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Solution{
    public static void main(String[] args)throws Exception{
        StringBuilder sb =  new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int l = Integer.parseInt(br.readLine());
        
        for(int i=1; i<=l; i++){
            int ll = Integer.parseInt(br.readLine());
            int speed = 0;
            int dis = 0;
            for(int j=0; j<ll; j++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int n1 = Integer.parseInt(st.nextToken());
                if(n1==0){ // 0 일때 유지
                    dis += speed;
                    continue;
                }
                int n2 = Integer.parseInt(st.nextToken());
                speed += (n1==1) ? n2 : n2*-1;
                if(speed<0){
                    speed = 0;
                    continue;
                }
                dis += speed;
            }
            sb.append("#").append(i).append(" ").append(dis).append("\n");                                                                    
        }
        System.out.println(sb);
    }
}